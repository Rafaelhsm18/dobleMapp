package doblem.app.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Etapas_Maestro")
public class EtapaMaestro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Etapa")
    private Integer id;

    @Column(name = "Nombre_Etapa", nullable = false, unique = true)
    private String nombreEtapa;

    @Column(name = "Descripcion_General")
    private String descripcionGeneral;
    
    // --- Getters y Setters ---
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreEtapa() {
		return nombreEtapa;
	}

	public void setNombreEtapa(String nombreEtapa) {
		this.nombreEtapa = nombreEtapa;
	}

	public String getDescripcionGeneral() {
		return descripcionGeneral;
	}

	public void setDescripcionGeneral(String descripcionGeneral) {
		this.descripcionGeneral = descripcionGeneral;
	}
}