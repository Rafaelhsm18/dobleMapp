package doblem.app.services;

import doblem.app.modelos.Formula;
import doblem.app.modelos.IngredienteFormula;
import doblem.app.modelos.Producto;
import doblem.app.repository.FormulaRepository;
import doblem.app.repository.LoteProveedorRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class StockService {

    private final FormulaRepository formulaRepository;
    private final LoteProveedorRepository loteProveedorRepository;

    public StockService(FormulaRepository formulaRepository, LoteProveedorRepository loteProveedorRepository) {
        this.formulaRepository = formulaRepository;
        this.loteProveedorRepository = loteProveedorRepository;
    }

    public List<Map<String, Object>> verificarStockParaLote(Producto producto, BigDecimal cantidadAProducir) {
        Optional<Formula> formulaOpt = formulaRepository.findByProductoId(producto.getId());
        if (formulaOpt.isEmpty() || formulaOpt.get().getCantidadProducida().compareTo(BigDecimal.ZERO) == 0) {
            return Collections.emptyList();
        }

        Formula formula = formulaOpt.get();
        List<Map<String, Object>> resultado = new ArrayList<>();

        // Calculamos cuántas "tandas" de la receta necesitamos
        BigDecimal multiplicador = cantidadAProducir.divide(formula.getCantidadProducida(), 4, RoundingMode.CEILING);

        for (IngredienteFormula ingrediente : formula.getIngredientes()) {
            Map<String, Object> item = new LinkedHashMap<>();
            BigDecimal cantidadRequerida = ingrediente.getCantidad().multiply(multiplicador);
            
            // Obtenemos el stock actual de la materia prima
            BigDecimal stockDisponible = loteProveedorRepository.sumCantidadRecibidaByMateriaPrimaId(ingrediente.getMateriaPrima().getId());
            if (stockDisponible == null) {
                stockDisponible = BigDecimal.ZERO;
            }

            item.put("materiaPrima", ingrediente.getMateriaPrima().getNombre());
            item.put("requerido", cantidadRequerida.stripTrailingZeros().toPlainString());
            item.put("disponible", stockDisponible.stripTrailingZeros().toPlainString());
            item.put("unidad", ingrediente.getUnidadMedida());
            item.put("suficiente", stockDisponible.compareTo(cantidadRequerida) >= 0);
            
            resultado.add(item);
        }

        return resultado;
    }
}