package doblem.app.services;

import doblem.app.modelos.IncidenciaLote;
import doblem.app.repository.IncidenciaLoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IncidenciaLoteService {

    private final IncidenciaLoteRepository repository;

    public IncidenciaLoteService(IncidenciaLoteRepository repository) {
        this.repository = repository;
    }

    public List<IncidenciaLote> findByLoteId(Integer loteId) {
        return repository.findByLoteProductoIdOrderByFechaReporteDesc(loteId);
    }

    public IncidenciaLote findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void save(IncidenciaLote incidencia) {
        repository.save(incidencia);
    }
    
    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void marcarComoResuelta(Integer incidenciaId) {
        IncidenciaLote incidencia = findById(incidenciaId);
        if (incidencia != null) {
            incidencia.setResuelta(true);
            save(incidencia);
        }
    }
}