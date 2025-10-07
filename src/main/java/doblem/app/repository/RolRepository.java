package doblem.app.repository;

import doblem.app.modelos.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Roles, Integer> {
}