package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Registro_Etapas_Lote")
public class RegistroEtapasLote extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Registro_Etapa")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Lote", nullable = false)
    private LoteProducto loteProducto;

    @ManyToOne
    @JoinColumn(name = "ID_Etapa", nullable = false)
    private EtapaMaestro etapa;

    @ManyToOne
    @JoinColumn(name = "ID_Empleado")
    private Empleados empleado;

    @Column(name = "Fecha_Completado")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaCompletado;

    @Column(name = "Observaciones")
    private String observaciones;
    
    // --- Getters y Setters ---
 // --- CAMPO NUEVO AÑADIDO ---
    @Column(name = "confirmado")
    private Boolean confirmado;

    // --- AÑADE SU GETTER Y SETTER ---
	public Boolean getConfirmado() {
		return confirmado;
	}

	public void setConfirmado(Boolean confirmado) {
		this.confirmado = confirmado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LoteProducto getLoteProducto() {
		return loteProducto;
	}

	public void setLoteProducto(LoteProducto loteProducto) {
		this.loteProducto = loteProducto;
	}

	public EtapaMaestro getEtapa() {
		return etapa;
	}

	public void setEtapa(EtapaMaestro etapa) {
		this.etapa = etapa;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	public LocalDateTime getFechaCompletado() {
		return fechaCompletado;
	}

	public void setFechaCompletado(LocalDateTime fechaCompletado) {
		this.fechaCompletado = fechaCompletado;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
}