package doblem.app.controller;

import doblem.app.modelos.TipoProducto;
import doblem.app.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipos-producto")
public class TipoProductoController {

    private final TipoProductoService tipoProductoService;

    @Autowired
    public TipoProductoController(TipoProductoService tipoProductoService) {
        this.tipoProductoService = tipoProductoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("tipos", tipoProductoService.findAll());
        return "tiposproducto/tipos-producto";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("tipo", new TipoProducto());
        return "tiposproducto/tipo-producto-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute TipoProducto tipo) {
        tipoProductoService.save(tipo);
        return "redirect:/tipos-producto";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id) {
        tipoProductoService.deleteById(id);
        return "redirect:/tipos-producto";
    }
}