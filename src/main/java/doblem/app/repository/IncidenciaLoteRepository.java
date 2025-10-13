package doblem.app.repository;

import doblem.app.modelos.IncidenciaLote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidenciaLoteRepository extends JpaRepository<IncidenciaLote, Integer> {
    // Busca todas las incidencias por el ID del lote, ordenadas por fecha de reporte (más recientes primero)
    List<IncidenciaLote> findByLoteProductoIdOrderByFechaReporteDesc(Integer loteProductoId);
}