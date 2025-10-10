package doblem.app.modelos;

import java.io.Serializable;
import java.util.Objects;

public class PlantillaProcesoId implements Serializable {

    private Integer tipoProducto;
    private Integer etapa;

    // Constructor, getters, setters, equals y hashCode son obligatorios
    public PlantillaProcesoId() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantillaProcesoId that = (PlantillaProcesoId) o;
        return Objects.equals(tipoProducto, that.tipoProducto) && Objects.equals(etapa, that.etapa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipoProducto, etapa);
    }
    
    // Getters y Setters
	public Integer getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(Integer tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Integer getEtapa() {
		return etapa;
	}

	public void setEtapa(Integer etapa) {
		this.etapa = etapa;
	}
}