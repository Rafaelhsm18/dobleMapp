package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Lotes_Proveedor")
public class LoteProveedor extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LoteProveedor")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MateriaPrima", nullable = false)
    private MateriaPrima materiaPrima;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Proveedor", nullable = false)
    private Proveedor proveedor;

    @Column(name = "CodigoLote", nullable = false)
    private String codigoLote;

    @Column(name = "CantidadRecibida", nullable = false)
    private BigDecimal cantidadRecibida;

    @Column(name = "UnidadMedida", nullable = false)
    private String unidadMedida;

    @Column(name = "FechaRecepcion", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaRecepcion;

    @Column(name = "FechaCaducidad")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCaducidad;

    // --- Getters y Setters ---
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MateriaPrima getMateriaPrima() {
		return materiaPrima;
	}

	public void setMateriaPrima(MateriaPrima materiaPrima) {
		this.materiaPrima = materiaPrima;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public String getCodigoLote() {
		return codigoLote;
	}

	public void setCodigoLote(String codigoLote) {
		this.codigoLote = codigoLote;
	}

	public BigDecimal getCantidadRecibida() {
		return cantidadRecibida;
	}

	public void setCantidadRecibida(BigDecimal cantidadRecibida) {
		this.cantidadRecibida = cantidadRecibida;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public LocalDate getFechaRecepcion() {
		return fechaRecepcion;
	}

	public void setFechaRecepcion(LocalDate fechaRecepcion) {
		this.fechaRecepcion = fechaRecepcion;
	}

	public LocalDate getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(LocalDate fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
}