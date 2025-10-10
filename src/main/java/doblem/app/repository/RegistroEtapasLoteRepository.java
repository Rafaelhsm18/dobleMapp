package doblem.app.repository;

import doblem.app.modelos.RegistroEtapasLote;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistroEtapasLoteRepository extends JpaRepository<RegistroEtapasLote, Integer> {
    List<RegistroEtapasLote> findByLoteProductoIdOrderByFechaCompletadoDesc(Integer loteId);
}