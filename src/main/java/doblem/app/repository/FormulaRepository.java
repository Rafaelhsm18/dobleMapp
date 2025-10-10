package doblem.app.repository;

import doblem.app.modelos.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FormulaRepository extends JpaRepository<Formula, Integer> {
    Optional<Formula> findByProductoId(Integer productoId);
}