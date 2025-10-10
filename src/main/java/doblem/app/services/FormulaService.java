package doblem.app.services;

import doblem.app.modelos.*;
import doblem.app.repository.FormulaRepository;
import doblem.app.repository.IngredienteFormulaRepository;
import doblem.app.repository.MateriaPrimaRepository;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FormulaService {

    private final FormulaRepository formulaRepository;
    private final IngredienteFormulaRepository ingredienteRepository;
    private final MateriaPrimaRepository materiaPrimaRepository; // Añadido

    public FormulaService(FormulaRepository formulaRepository, IngredienteFormulaRepository ingredienteRepository, MateriaPrimaRepository materiaPrimaRepository) {
        this.formulaRepository = formulaRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.materiaPrimaRepository = materiaPrimaRepository; // Añadido
    }

    // ... findOrCreateFormulaByProducto no cambia ...
    @Transactional
    public Formula findOrCreateFormulaByProducto(Producto producto) {
        return formulaRepository.findByProductoId(producto.getId()).orElseGet(() -> {
            Formula nuevaFormula = new Formula();
            nuevaFormula.setProducto(producto);
            nuevaFormula.setNombre("Fórmula para " + producto.getNombre());
            
            // --- LÍNEAS AÑADIDAS PARA LA SOLUCIÓN 👇 ---
            nuevaFormula.setCantidadProducida(new BigDecimal("1.0")); // Valor por defecto: 1
            nuevaFormula.setUnidadProducida("ud"); // Valor por defecto: "ud" (unidades)
            
            return formulaRepository.save(nuevaFormula);
        });
    }
    
    public void saveIngrediente(IngredienteFormula ingrediente) {
        ingredienteRepository.save(ingrediente);
    }
    
    // --- LÓGICA DE BORRADO ACTUALIZADA ---
    @Transactional
    public void deleteIngrediente(Integer formulaId, Integer materiaPrimaId) {
        Formula formula = formulaRepository.findById(formulaId).orElse(null);
        MateriaPrima materiaPrima = materiaPrimaRepository.findById(materiaPrimaId).orElse(null);

        if (formula != null && materiaPrima != null) {
            // Gracias a "orphanRemoval=true" en la entidad Formula,
            // al quitar el ingrediente de la lista, JPA lo borrará de la BBDD.
            formula.getIngredientes().removeIf(ing -> 
                ing.getMateriaPrima().getId().equals(materiaPrimaId)
            );
        }
    }
}