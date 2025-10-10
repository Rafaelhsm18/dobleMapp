package doblem.app.controller;

import doblem.app.modelos.Producto;
import doblem.app.services.ProductoService;
import doblem.app.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final TipoProductoService tipoProductoService;

    @Autowired
    public ProductoController(ProductoService productoService, TipoProductoService tipoProductoService) {
        this.productoService = productoService;
        this.tipoProductoService = tipoProductoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("tipos", tipoProductoService.findAll());
        return "productos/producto-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("producto", productoService.findById(id));
        model.addAttribute("tipos", tipoProductoService.findAll());
        return "productos/producto-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.save(producto);
        return "redirect:/productos";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id) {
        productoService.deleteById(id);
        return "redirect:/productos";
    }
}