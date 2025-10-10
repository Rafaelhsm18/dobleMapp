package doblem.app.services;

import doblem.app.modelos.TipoProducto;
import doblem.app.repository.TipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoProductoService {

    private final TipoProductoRepository tipoProductoRepository;

    @Autowired
    public TipoProductoService(TipoProductoRepository tipoProductoRepository) {
        this.tipoProductoRepository = tipoProductoRepository;
    }

    public List<TipoProducto> findAll() {
        return tipoProductoRepository.findAll();
    }

    public TipoProducto findById(Integer id) {
        return tipoProductoRepository.findById(id).orElse(null);
    }

    public void save(TipoProducto tipoProducto) {
        tipoProductoRepository.save(tipoProducto);
    }

    public void deleteById(Integer id) {
        tipoProductoRepository.deleteById(id);
    }
}