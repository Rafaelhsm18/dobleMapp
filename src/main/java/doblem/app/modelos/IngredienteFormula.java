package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "Ingredientes_Formula")
// 👇 Indicamos que la clave primaria es la clase que hemos creado
@IdClass(IngredienteFormulaId.class)
public class IngredienteFormula {

    // Ya no usamos un ID simple. La clave es la combinación de los dos campos siguientes.
    
    @Id // Parte 1 de la clave compuesta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Formula")
    private Formula formula;

    @Id // Parte 2 de la clave compuesta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MateriaPrima")
    private MateriaPrima materiaPrima;

    @Column(name = "Cantidad", nullable = false)
    private BigDecimal cantidad;

    @Column(name = "UnidadMedida", nullable = false)
    private String unidadMedida;

    // --- Getters y Setters explícitos por si acaso ---
	public Formula getFormula() {
		return formula;
	}

	public void setFormula(Formula formula) {
		this.formula = formula;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
}