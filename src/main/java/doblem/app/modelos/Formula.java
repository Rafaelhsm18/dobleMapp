package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Formulas")
public class Formula extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Formula")
    private Integer id;

    // Usamos OneToOne porque, por ahora, un producto tendrá una única fórmula activa.
    @OneToOne
    @JoinColumn(name = "ID_Producto", nullable = false)
    private Producto producto;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "CantidadProducida")
    private BigDecimal cantidadProducida;

    @Column(name = "UnidadProducida")
    private String unidadProducida;
    
    // Una fórmula tiene muchos ingredientes.
    // Cascade.ALL hace que si borramos una fórmula, se borren sus ingredientes.
    // OrphanRemoval=true hace que si quitamos un ingrediente de la lista, se borre de la BD.
    @OneToMany(mappedBy = "formula", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<IngredienteFormula> ingredientes = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getCantidadProducida() {
		return cantidadProducida;
	}

	public void setCantidadProducida(BigDecimal cantidadProducida) {
		this.cantidadProducida = cantidadProducida;
	}

	public String getUnidadProducida() {
		return unidadProducida;
	}

	public void setUnidadProducida(String unidadProducida) {
		this.unidadProducida = unidadProducida;
	}

	public Set<IngredienteFormula> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(Set<IngredienteFormula> ingredientes) {
		this.ingredientes = ingredientes;
	}

    // --- Getters y Setters ---
    // (Añade los getters y setters explícitos si continúas teniendo problemas con Lombok)
    
}