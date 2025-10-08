package doblem.app.controller;

import doblem.app.modelos.Equipo;
import doblem.app.modelos.MantenimientoEquipo;
import doblem.app.services.EmpleadoService;
import doblem.app.services.EquipoService;
import doblem.app.services.MantenimientoEquipoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/equipos/{equipoId}/mantenimientos")
public class MantenimientoEquipoController {

    private final MantenimientoEquipoService mantenimientoService;
    private final EquipoService equipoService;
    private final EmpleadoService empleadoService;

    public MantenimientoEquipoController(MantenimientoEquipoService mantenimientoService, EquipoService equipoService, EmpleadoService empleadoService) {
        this.mantenimientoService = mantenimientoService;
        this.equipoService = equipoService;
        this.empleadoService = empleadoService;
    }

    // Muestra la lista de mantenimientos de un equipo
    @GetMapping
    public String listarMantenimientos(@PathVariable Integer equipoId, Model model) {
        Equipo equipo = equipoService.findById(equipoId);
        List<MantenimientoEquipo> mantenimientos = mantenimientoService.findByEquipoId(equipoId);
        model.addAttribute("equipo", equipo);
        model.addAttribute("mantenimientos", mantenimientos);
        return "mantenimientos/mantenimientos";
    }

    // Muestra el formulario para un nuevo mantenimiento
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(@PathVariable Integer equipoId, Model model) {
        Equipo equipo = equipoService.findById(equipoId);
        MantenimientoEquipo mantenimiento = new MantenimientoEquipo();
        mantenimiento.setEquipo(equipo);

        model.addAttribute("mantenimiento", mantenimiento);
        model.addAttribute("empleados", empleadoService.findAll()); // Pasa la lista de empleados
        return "mantenimientos/mantenimientoForm";
    }

    // Guarda un mantenimiento (nuevo o editado)
    @PostMapping("/guardar")
    public String guardarMantenimiento(@PathVariable Integer equipoId, @ModelAttribute MantenimientoEquipo mantenimiento) {
        Equipo equipo = equipoService.findById(equipoId);
        mantenimiento.setEquipo(equipo);
        mantenimientoService.save(mantenimiento);
        return "redirect:/equipos/" + equipoId + "/mantenimientos";
    }

    // Borra un mantenimiento
    @GetMapping("/borrar/{mantenimientoId}")
    public String borrarMantenimiento(@PathVariable Integer equipoId, @PathVariable Integer mantenimientoId) {
        mantenimientoService.deleteById(mantenimientoId);
        return "redirect:/equipos/" + equipoId + "/mantenimientos";
    }
}