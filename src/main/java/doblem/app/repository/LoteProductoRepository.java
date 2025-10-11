package doblem.app.repository;

import doblem.app.modelos.LoteProducto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteProductoRepository extends JpaRepository<LoteProducto, Integer> {
	
	 // Busca un LoteProducto por su código de lote interno
    Optional<LoteProducto> findByCodigoLoteInterno(String codigoLoteInterno);
}