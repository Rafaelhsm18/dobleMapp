package doblem.app.services;

import org.springframework.stereotype.Service;

import doblem.app.modelos.Empleados;
import doblem.app.repository.EmpleadoRepository;

import java.util.List;

@Service
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public List<Empleados> findAll() {
        return empleadoRepository.findAll();
    }

    public Empleados findById(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public void save(Empleados empleado) {
        empleadoRepository.save(empleado);
    }

    public void deleteById(Integer id) {
        empleadoRepository.deleteById(id);
    }
}