package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Stock_ProductoTerminado")
public class StockProductoTerminado extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Stock")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "ID_Lote", nullable = false, unique = true)
    private LoteProducto loteProducto;

    @ManyToOne
    @JoinColumn(name = "ID_Producto", nullable = false)
    private Producto producto;

    @Column(name = "Cantidad_Inicial", nullable = false)
    private BigDecimal cantidadInicial;

    @Column(name = "Cantidad_Actual", nullable = false)
    private BigDecimal cantidadActual;

    @Column(name = "Fecha_Entrada", nullable = false)
    private LocalDateTime fechaEntrada;

    @Column(name = "Ubicacion")
    private String ubicacion;
    
    // --- Getters y Setters ---
    public Integer getId() { return id; }

	public void setId(Integer id) {
		this.id = id;
	}

	public LoteProducto getLoteProducto() {
		return loteProducto;
	}

	public void setLoteProducto(LoteProducto loteProducto) {
		this.loteProducto = loteProducto;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public BigDecimal getCantidadInicial() {
		return cantidadInicial;
	}

	public void setCantidadInicial(BigDecimal cantidadInicial) {
		this.cantidadInicial = cantidadInicial;
	}

	public BigDecimal getCantidadActual() {
		return cantidadActual;
	}

	public void setCantidadActual(BigDecimal cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}