package doblem.app.services;

import doblem.app.modelos.PlantillaProceso;
import doblem.app.repository.PlantillaProcesoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlantillaProcesoService {

    private final PlantillaProcesoRepository repository;

    public PlantillaProcesoService(PlantillaProcesoRepository repository) {
        this.repository = repository;
    }

    public List<PlantillaProceso> findByTipoProductoId(Integer tipoProductoId) {
        return repository.findByTipoProductoIdOrderByOrdenAsc(tipoProductoId);
    }

    public void save(PlantillaProceso plantilla) {
        repository.save(plantilla);
    }

    public void delete(PlantillaProceso plantilla) {
        repository.delete(plantilla);
    }
}