package doblem.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import doblem.app.modelos.Equipo;
import doblem.app.services.EquipoService;
import java.util.List;

@Controller
@RequestMapping("/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    @GetMapping
    public String listarEquipos(Model model) {
        List<Equipo> equipos = equipoService.findAll();
        model.addAttribute("equipos", equipos);
        return "equipos/equipos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("equipo", new Equipo());
        return "equipos/equiposForm";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Equipo equipo = equipoService.findById(id);
        model.addAttribute("equipo", equipo);
        return "equipos/equiposForm";
    }

    @PostMapping("/guardar")
    public String guardarEquipo(@ModelAttribute Equipo equipo) {
        equipoService.save(equipo);
        return "redirect:/equipos";
    }

    @GetMapping("/borrar/{id}")
    public String borrarEquipo(@PathVariable Integer id) {
        equipoService.deleteById(id);
        return "redirect:/equipos";
    }
}