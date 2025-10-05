package doblem.app.controller;

//en el paquete ...controller
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import doblem.app.modelos.Empleados;
import doblem.app.services.EmpleadoService;

import java.util.List;

@Controller
@RequestMapping("/empleados") // Todas las URLs de este controlador empezarán con /empleados
public class EmpleadoController {

 private final EmpleadoService empleadoService;

 public EmpleadoController(EmpleadoService empleadoService) {
     this.empleadoService = empleadoService;
 }

 // Muestra la lista de todos los empleados
 @GetMapping
 public String listarEmpleados(Model model) {
     List<Empleados> empleados = empleadoService.findAll();
     model.addAttribute("empleados", empleados);
     return "empleados/lista"; // Devuelve el archivo templates/empleados/lista.html
 }

 // Muestra el formulario para crear un nuevo empleado
 @GetMapping("/nuevo")
 public String mostrarFormularioNuevo(Model model) {
     model.addAttribute("empleado", new Empleados());
     return "empleados/empleadosForm"; // Devuelve el archivo templates/empleados/formulario.html
 }
 
 // Muestra el formulario para editar un empleado existente
 @GetMapping("/editar/{id}")
 public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
     Empleados empleado = empleadoService.findById(id);
     model.addAttribute("empleado", empleado);
     return "empleados/formulario";
 }

 // Procesa el envío del formulario (tanto para crear como para actualizar)
 @PostMapping("/guardar")
 public String guardarEmpleado(@ModelAttribute Empleados empleado) {
     empleadoService.save(empleado);
     return "redirect:/empleados"; // Redirige a la lista de empleados
 }

 // Borra un empleado
 @GetMapping("/borrar/{id}")
 public String borrarEmpleado(@PathVariable Integer id) {
     empleadoService.deleteById(id);
     return "redirect:/empleados";
 }
}
