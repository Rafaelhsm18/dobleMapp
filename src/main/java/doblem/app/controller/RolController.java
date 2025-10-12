package doblem.app.controller;

import doblem.app.modelos.Roles;
import doblem.app.services.RolService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping
    public String listarRoles(Model model) {
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("newRol", new Roles()); // Para el formulario de añadir
        return "usuarios/roles"; // Apunta a la plantilla que crearemos ahora
    }

    @PostMapping("/guardar")
    public String guardarRol(@ModelAttribute Roles rol) {
        // Por convención de Spring Security, los roles deben empezar con "ROLE_"
        if (!rol.getNombre().startsWith("ROLE_")) {
            rol.setNombre("ROLE_" + rol.getNombre().toUpperCase().trim());
        }
        rolService.save(rol);
        return "redirect:/roles";
    }

    @GetMapping("/borrar/{id}")
    public String borrarRol(@PathVariable Integer id) {
        rolService.deleteById(id);
        return "redirect:/roles";
    }
}