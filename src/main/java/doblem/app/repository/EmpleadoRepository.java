package doblem.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import doblem.app.modelos.*;

public interface EmpleadoRepository extends JpaRepository<Empleados, Integer> {
}