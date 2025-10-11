package doblem.app.services;

import doblem.app.modelos.LoteProveedor;
import doblem.app.repository.LoteProveedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoteProveedorService {

    private final LoteProveedorRepository repository;

    public LoteProveedorService(LoteProveedorRepository repository) {
        this.repository = repository;
    }

	public List<LoteProveedor> findAll() {
		return repository.findAll();
	}

	public LoteProveedor findById(Integer id) {
		return repository.findById(id).orElse(null);
	}

	public void save(LoteProveedor lote) {
		repository.save(lote);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}
}