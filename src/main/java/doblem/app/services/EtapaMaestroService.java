package doblem.app.services;

import doblem.app.modelos.EtapaMaestro;
import doblem.app.repository.EtapaMaestroRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EtapaMaestroService {

    private final EtapaMaestroRepository repository;

    public EtapaMaestroService(EtapaMaestroRepository repository) {
        this.repository = repository;
    }

	public List<EtapaMaestro> findAll() {
		return repository.findAll();
	}

	public void save(EtapaMaestro etapa) {
		repository.save(etapa);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
	

    public EtapaMaestro findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

}