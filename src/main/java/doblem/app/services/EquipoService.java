package doblem.app.services;

import org.springframework.stereotype.Service;
import doblem.app.modelos.Equipo;
import doblem.app.repository.EquipoRepository;
import java.util.List;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    public List<Equipo> findAll() {
        return equipoRepository.findAll();
    }

    public Equipo findById(Integer id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public void save(Equipo equipo) {
        equipoRepository.save(equipo);
    }

    public void deleteById(Integer id) {
        equipoRepository.deleteById(id);
    }
}