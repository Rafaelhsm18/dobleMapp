package doblem.app.services;

import doblem.app.modelos.RegistroEtapasLote;
import doblem.app.repository.RegistroEtapasLoteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistroEtapasLoteService {

    private final RegistroEtapasLoteRepository repository;

    public RegistroEtapasLoteService(RegistroEtapasLoteRepository repository) {
        this.repository = repository;
    }

    public List<RegistroEtapasLote> findByLoteId(Integer loteId) {
        return repository.findByLoteProductoIdOrderByFechaCompletadoDesc(loteId);
    }

    public void save(RegistroEtapasLote registro) {
        repository.save(registro);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }
}