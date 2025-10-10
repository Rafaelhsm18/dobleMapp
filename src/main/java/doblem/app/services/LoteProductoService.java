package doblem.app.services;

import doblem.app.modelos.LoteProducto;
import doblem.app.repository.LoteProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoteProductoService {

    private final LoteProductoRepository loteProductoRepository;

    @Autowired
    public LoteProductoService(LoteProductoRepository loteProductoRepository) {
        this.loteProductoRepository = loteProductoRepository;
    }

    public List<LoteProducto> findAll() {
        return loteProductoRepository.findAll();
    }

    public LoteProducto findById(Integer id) {
        return loteProductoRepository.findById(id).orElse(null);
    }

    public void save(LoteProducto loteProducto) {
        loteProductoRepository.save(loteProducto);
    }

    public void deleteById(Integer id) {
        loteProductoRepository.deleteById(id);
    }
}