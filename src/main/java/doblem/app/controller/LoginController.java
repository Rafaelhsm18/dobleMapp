package doblem.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    // Redirección de la raíz a la página de empleados
    @GetMapping("/")
    public String home() {
        return "redirect:/empleados";
    }
}