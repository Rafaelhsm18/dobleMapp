package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Plantillas_Proceso")
@IdClass(PlantillaProcesoId.class)
public class PlantillaProceso {

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_TipoProducto")
    private TipoProducto tipoProducto;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_Etapa")
    private EtapaMaestro etapa;

    @Column(name = "Orden", nullable = false)
    private int orden;

    // --- Getters y Setters ---
    public TipoProducto getTipoProducto() { return tipoProducto; }
    public void setTipoProducto(TipoProducto tipoProducto) { this.tipoProducto = tipoProducto; }
    public EtapaMaestro getEtapa() { return etapa; }
    public void setEtapa(EtapaMaestro etapa) { this.etapa = etapa; }
    public int getOrden() { return orden; }
    public void setOrden(int orden) { this.orden = orden; }
}