package doblem.app.controller;

import doblem.app.modelos.Usuarios;
import doblem.app.services.EmpleadoService;
import doblem.app.services.RolService;
import doblem.app.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final EmpleadoService empleadoService;
    private final RolService rolService;

    public UsuarioController(UsuarioService usuarioService, EmpleadoService empleadoService, RolService rolService) {
        this.usuarioService = usuarioService;
        this.empleadoService = empleadoService;
        this.rolService = rolService;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "usuarios/usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuarios());
        model.addAttribute("empleados", empleadoService.findAll());
        model.addAttribute("roles", rolService.findAll());
        return "usuarios/usuario-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        Usuarios usuario = usuarioService.findById(id);
        // Evitamos que la contraseña cifrada se envíe a la vista
        usuario.setPassword("");
        model.addAttribute("usuario", usuario);
        model.addAttribute("empleados", empleadoService.findAll());
        model.addAttribute("roles", rolService.findAll());
        return "usuarios/usuario-form";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute Usuarios usuario) {
        usuarioService.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/borrar/{id}")
    public String borrarUsuario(@PathVariable Integer id) {
        usuarioService.deleteById(id);
        return "redirect:/usuarios";
    }
}