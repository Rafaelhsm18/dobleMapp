package doblem.app.modelos;

import java.io.Serializable;
import java.util.Objects;

// No es una entidad, es una clase para representar la clave
public class IngredienteFormulaId implements Serializable {

    private Integer formula; // El nombre debe coincidir con el campo en IngredienteFormula
    private Integer materiaPrima; // El nombre debe coincidir con el campo en IngredienteFormula

    // Constructor vacío, getters, setters, equals() y hashCode() son obligatorios

    public IngredienteFormulaId() {
    }

    public IngredienteFormulaId(Integer formula, Integer materiaPrima) {
        this.formula = formula;
        this.materiaPrima = materiaPrima;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredienteFormulaId that = (IngredienteFormulaId) o;
        return Objects.equals(formula, that.formula) &&
               Objects.equals(materiaPrima, that.materiaPrima);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formula, materiaPrima);
    }

    // Getters y Setters
    public Integer getFormula() { return formula; }
    public void setFormula(Integer formula) { this.formula = formula; }
    public Integer getMateriaPrima() { return materiaPrima; }
    public void setMateriaPrima(Integer materiaPrima) { this.materiaPrima = materiaPrima; }
}