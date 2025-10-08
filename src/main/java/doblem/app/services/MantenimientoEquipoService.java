package doblem.app.services;

import doblem.app.modelos.MantenimientoEquipo;
import doblem.app.repository.MantenimientoEquipoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MantenimientoEquipoService {

    private final MantenimientoEquipoRepository mantenimientoRepository;

    public MantenimientoEquipoService(MantenimientoEquipoRepository mantenimientoRepository) {
        this.mantenimientoRepository = mantenimientoRepository;
    }

    public List<MantenimientoEquipo> findByEquipoId(Integer equipoId) {
        return mantenimientoRepository.findByEquipoId(equipoId);
    }

    public void save(MantenimientoEquipo mantenimiento) {
        mantenimientoRepository.save(mantenimiento);
    }
    
    public void deleteById(Integer id) {
        mantenimientoRepository.deleteById(id);
    }
}