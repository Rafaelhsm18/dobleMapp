package doblem.app.services;

import doblem.app.modelos.Proveedor;
import doblem.app.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Autowired
    public ProveedorService(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> findAll() {
        return proveedorRepository.findAll();
    }

    public Proveedor findById(Integer id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public void save(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    public void deleteById(Integer id) {
        proveedorRepository.deleteById(id);
    }
}