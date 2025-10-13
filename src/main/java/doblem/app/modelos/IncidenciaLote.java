package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Incidencias_Lote")
public class IncidenciaLote extends EntidadAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Incidencia")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Lote", nullable = false)
    private LoteProducto loteProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Empleado_Reporta")
    private Empleados empleadoReporta;

    @Column(name = "Fecha_Reporte", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime fechaReporte;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "Resuelta")
    private Boolean resuelta = false; // Valor por defecto

    // --- Getters y Setters ---
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LoteProducto getLoteProducto() { return loteProducto; }
    public void setLoteProducto(LoteProducto loteProducto) { this.loteProducto = loteProducto; }

    public Empleados getEmpleadoReporta() { return empleadoReporta; }
    public void setEmpleadoReporta(Empleados empleadoReporta) { this.empleadoReporta = empleadoReporta; }

    public LocalDateTime getFechaReporte() { return fechaReporte; }
    public void setFechaReporte(LocalDateTime fechaReporte) { this.fechaReporte = fechaReporte; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Boolean getResuelta() { return resuelta; }
    public void setResuelta(Boolean resuelta) { this.resuelta = resuelta; }
}