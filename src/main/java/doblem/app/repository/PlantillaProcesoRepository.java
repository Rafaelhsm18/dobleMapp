package doblem.app.repository;

import doblem.app.modelos.PlantillaProceso;
import doblem.app.modelos.PlantillaProcesoId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PlantillaProcesoRepository extends JpaRepository<PlantillaProceso, PlantillaProcesoId> {
    // Busca todas las etapas de una plantilla, ordenadas por el campo 'orden'
    List<PlantillaProceso> findByTipoProductoIdOrderByOrdenAsc(Integer tipoProductoId);
}