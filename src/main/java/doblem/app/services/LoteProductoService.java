package doblem.app.services;

import doblem.app.modelos.LoteProducto;
import doblem.app.repository.LoteProductoRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoteProductoService {

    private final LoteProductoRepository loteProductoRepository;
    private final PlantillaProcesoService plantillaService;
    private final RegistroEtapasLoteService registroEtapasService;
    private final StockProductoTerminadoService stockService;

    // Usamos @Lazy para romper una posible dependencia circular entre servicios
    @Autowired
    public LoteProductoService(LoteProductoRepository loteProductoRepository, @Lazy PlantillaProcesoService plantillaService, @Lazy RegistroEtapasLoteService registroEtapasService,@Lazy StockProductoTerminadoService stockService) {
        this.loteProductoRepository = loteProductoRepository;
        this.plantillaService = plantillaService;
        this.registroEtapasService = registroEtapasService;
        this.stockService = stockService;
    }

    public List<LoteProducto> findAll() {
        return loteProductoRepository.findAll();
    }

    public LoteProducto findById(Integer id) {
        return loteProductoRepository.findById(id).orElse(null);
    }

    public void save(LoteProducto loteProducto) {
        loteProductoRepository.save(loteProducto);
    }

    public void deleteById(Integer id) {
        loteProductoRepository.deleteById(id);
    }
    
    @Transactional
    public void actualizarEstadoLote(Integer loteId) {
        LoteProducto lote = findById(loteId);
        if (lote == null || "Cancelado".equals(lote.getEstado())) {
            // Si el lote no existe o ya está cancelado, no hacemos nada
            return;
        }

        // Obtenemos la plantilla y los registros
        int numEtapasRequeridas = plantillaService.findByTipoProductoId(lote.getProducto().getTipoProducto().getId()).size();
        int numEtapasCompletadas = registroEtapasService.findByLoteId(loteId).size();

        // Aplicamos la lógica para cambiar el estado
        if (numEtapasCompletadas == 0) {
            lote.setEstado("Pendiente");
        } else if (numEtapasCompletadas < numEtapasRequeridas) {
            lote.setEstado("En Proceso");
        } else if (numEtapasCompletadas >= numEtapasRequeridas && numEtapasRequeridas > 0) {
            lote.setEstado("Finalizado");
        }
        
        if (numEtapasCompletadas >= numEtapasRequeridas && numEtapasRequeridas > 0) {
            lote.setEstado("Finalizado");
            // Al finalizar, llamamos al servicio de stock
            stockService.crearOActualizarStockParaLote(lote);
        }
        
        save(lote);
    }
    
    public Optional<LoteProducto> findByCodigoLoteInterno(String codigo) {
        return loteProductoRepository.findByCodigoLoteInterno(codigo);
    }
}