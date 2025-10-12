package doblem.app.repository;

import doblem.app.modelos.StockProductoTerminado;
import doblem.app.modelos.dto.StockResumenDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StockProductoTerminadoRepository extends JpaRepository<StockProductoTerminado, Integer> {
    Optional<StockProductoTerminado> findByLoteProductoId(Integer loteId);
    
    @Query("SELECT new doblem.app.modelos.dto.StockResumenDTO(s.producto, SUM(s.cantidadActual)) " +
            "FROM StockProductoTerminado s GROUP BY s.producto")
     List<StockResumenDTO> getResumenDeStock();
}