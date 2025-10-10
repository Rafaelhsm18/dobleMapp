package doblem.app.controller;

import doblem.app.modelos.EtapaMaestro;
import doblem.app.services.EtapaMaestroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/etapas-maestro")
public class EtapaMaestroController {

    private final EtapaMaestroService etapaService;

    public EtapaMaestroController(EtapaMaestroService etapaService) {
        this.etapaService = etapaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("etapas", etapaService.findAll());
        return "etapas/etapas-maestro";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute EtapaMaestro etapa) {
        etapaService.save(etapa);
        return "redirect:/etapas-maestro";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(@PathVariable Integer id) {
        etapaService.deleteById(id);
        return "redirect:/etapas-maestro";
    }
    
    
    // --- MÉTODOS NUEVOS PARA EL FORMULARIO ---
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("etapa", new EtapaMaestro());
        return "etapas/etapa-maestro-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        model.addAttribute("etapa", etapaService.findById(id));
        return "etapas/etapa-maestro-form";
    }
}