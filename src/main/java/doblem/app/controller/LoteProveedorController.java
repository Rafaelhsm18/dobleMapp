package doblem.app.controller;

import doblem.app.modelos.LoteProveedor;
import doblem.app.services.LoteProveedorService;
import doblem.app.services.MateriaPrimaService;
import doblem.app.services.ProveedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lotes-proveedor")
public class LoteProveedorController {

    private final LoteProveedorService loteProveedorService;
    private final MateriaPrimaService materiaPrimaService;
    private final ProveedorService proveedorService;

    public LoteProveedorController(LoteProveedorService loteProveedorService, MateriaPrimaService materiaPrimaService, ProveedorService proveedorService) {
        this.loteProveedorService = loteProveedorService;
        this.materiaPrimaService = materiaPrimaService;
        this.proveedorService = proveedorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lotes", loteProveedorService.findAll());
        return "lotesproveedor/lotes-proveedor";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("lote", new LoteProveedor());
        model.addAttribute("materiasPrimas", materiaPrimaService.findAll());
        model.addAttribute("proveedores", proveedorService.findAll());
        return "lotesproveedor/lote-proveedor-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("lote", loteProveedorService.findById(id));
        model.addAttribute("materiasPrimas", materiaPrimaService.findAll());
        model.addAttribute("proveedores", proveedorService.findAll());
        return "lotesproveedor/lote-proveedor-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute LoteProveedor lote) {
        loteProveedorService.save(lote);
        return "redirect:/lotes-proveedor";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id) {
        loteProveedorService.deleteById(id);
        return "redirect:/lotes-proveedor";
    }
}