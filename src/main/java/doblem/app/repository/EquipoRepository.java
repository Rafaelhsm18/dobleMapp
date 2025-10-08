package doblem.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import doblem.app.modelos.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
}
