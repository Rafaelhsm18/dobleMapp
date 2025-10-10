package doblem.app.controller;

import doblem.app.modelos.LoteProducto;
import doblem.app.services.LoteProductoService;
import doblem.app.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/produccion")
public class ProduccionController {

    private final LoteProductoService loteProductoService;
    private final ProductoService productoService;

    @Autowired
    public ProduccionController(LoteProductoService loteProductoService, ProductoService productoService) {
        this.loteProductoService = loteProductoService;
        this.productoService = productoService;
    }

    @GetMapping
    public String listarLotes(Model model) {
        model.addAttribute("lotes", loteProductoService.findAll());
        return "produccion/produccion";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        LoteProducto lote = new LoteProducto();
        model.addAttribute("lote", lote);
        model.addAttribute("productos", productoService.findAll());
        return "produccion/produccion-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("lote", loteProductoService.findById(id));
        model.addAttribute("productos", productoService.findAll());
        return "produccion/produccion-form";
    }

    @PostMapping("/guardar")
    public String guardarLote(@ModelAttribute LoteProducto lote) {
        loteProductoService.save(lote);
        return "redirect:/produccion";
    }

    @GetMapping("/borrar/{id}")
    public String borrarLote(@PathVariable Integer id) {
        loteProductoService.deleteById(id);
        return "redirect:/produccion";
    }
}