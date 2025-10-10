package doblem.app.services;

import doblem.app.modelos.MateriaPrima;
import doblem.app.repository.MateriaPrimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaPrimaService {

    private final MateriaPrimaRepository materiaPrimaRepository;

    @Autowired
    public MateriaPrimaService(MateriaPrimaRepository materiaPrimaRepository) {
        this.materiaPrimaRepository = materiaPrimaRepository;
    }

    public List<MateriaPrima> findAll() {
        return materiaPrimaRepository.findAll();
    }

    public MateriaPrima findById(Integer id) {
        return materiaPrimaRepository.findById(id).orElse(null);
    }

    public void save(MateriaPrima materiaPrima) {
        materiaPrimaRepository.save(materiaPrima);
    }

    public void deleteById(Integer id) {
        materiaPrimaRepository.deleteById(id);
    }
}