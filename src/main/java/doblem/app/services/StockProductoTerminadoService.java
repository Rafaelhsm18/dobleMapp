package doblem.app.services;

import doblem.app.modelos.LoteProducto;
import doblem.app.modelos.StockProductoTerminado;
import doblem.app.modelos.dto.StockResumenDTO;
import doblem.app.repository.StockProductoTerminadoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockProductoTerminadoService {

    private final StockProductoTerminadoRepository repository;

    public StockProductoTerminadoService(StockProductoTerminadoRepository repository) {
        this.repository = repository;
    }

    public Page<StockProductoTerminado> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // --- MÉTODO NUEVO para el resumen ---
    public List<StockResumenDTO> getResumenStock() {
        return repository.getResumenDeStock();
    }

    // Lógica para crear o actualizar el stock de un lote
    public void crearOActualizarStockParaLote(LoteProducto lote) {
        if (!"Finalizado".equals(lote.getEstado())) {
            return; // Solo procesamos lotes finalizados
        }

        StockProductoTerminado stock = repository.findByLoteProductoId(lote.getId())
                .orElse(new StockProductoTerminado()); // Crea uno nuevo si no existe

        stock.setLoteProducto(lote);
        stock.setProducto(lote.getProducto());
        stock.setCantidadInicial(lote.getCantidadProducidaReal());
        stock.setCantidadActual(lote.getCantidadProducidaReal()); // Asumimos que al inicio, la cantidad actual es la total
        stock.setFechaEntrada(LocalDateTime.now());
        // stock.setUbicacion("Almacén Principal"); // Puedes añadir una ubicación por defecto

        repository.save(stock);
    }
}