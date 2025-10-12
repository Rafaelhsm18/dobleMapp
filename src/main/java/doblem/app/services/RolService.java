package doblem.app.services;

import doblem.app.modelos.Roles;
import doblem.app.repository.RolRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Roles> findAll() {
        return rolRepository.findAll();
    }
    public void save(Roles rol) {
        rolRepository.save(rol);
    }

    public void deleteById(Integer id) {
        rolRepository.deleteById(id);
    }
}