package doblem.app.repository;

import doblem.app.modelos.MantenimientoEquipo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MantenimientoEquipoRepository extends JpaRepository<MantenimientoEquipo, Integer> {
    
    // Spring Data JPA creará automáticamente la consulta para buscar por el ID del equipo
    List<MantenimientoEquipo> findByEquipoId(Integer equipoId);
}