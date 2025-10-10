package doblem.app.repository;

import doblem.app.modelos.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoProductoRepository extends JpaRepository<TipoProducto, Integer> {
}