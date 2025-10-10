package doblem.app.repository;

import doblem.app.modelos.LoteProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteProductoRepository extends JpaRepository<LoteProducto, Integer> {
}