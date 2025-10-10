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
@Table(name = "Lotes_Producto")
public class LoteProducto extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Lote")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Producto", nullable = false)
    private Producto producto;

    @Column(name = "Codigo_Lote_Interno", nullable = false, unique = true)
    private String codigoLoteInterno;

//    @Column(name = "Fecha_Creacion", nullable = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate fechaCreacion;

    @Column(name = "Cantidad_Teorica", nullable = false)
    private BigDecimal cantidadTeorica;

    @Column(name = "Cantidad_Producida_Real")
    private BigDecimal cantidadProducidaReal;
    
    @Column(name = "Cantidad_Disponible")
    private BigDecimal cantidadDisponible;

    @Column(name = "Estado")
    private String estado = "Pendiente"; // Valor por defecto

    // --- Getters y Setters ---
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

	public String getCodigoLoteInterno() {
		return codigoLoteInterno;
	}

	public void setCodigoLoteInterno(String codigoLoteInterno) {
		this.codigoLoteInterno = codigoLoteInterno;
	}

//	public LocalDate getFechaCreacion() {
//		return fechaCreacion;
//	}
//
//	public void setFechaCreacion(LocalDate fechaCreacion) {
//		this.fechaCreacion = fechaCreacion;
//	}

	public BigDecimal getCantidadTeorica() {
		return cantidadTeorica;
	}

	public void setCantidadTeorica(BigDecimal cantidadTeorica) {
		this.cantidadTeorica = cantidadTeorica;
	}

	public BigDecimal getCantidadProducidaReal() {
		return cantidadProducidaReal;
	}

	public void setCantidadProducidaReal(BigDecimal cantidadProducidaReal) {
		this.cantidadProducidaReal = cantidadProducidaReal;
	}

	public BigDecimal getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(BigDecimal cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}