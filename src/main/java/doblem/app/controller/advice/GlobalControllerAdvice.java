package doblem.app.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * Añade la URI de la petición actual al modelo antes de que se renderice cualquier vista.
     * Esto nos permite acceder a la ruta en el layout para resaltar el menú activo.
     *
     * @param request La petición HTTP actual.
     * @return La URI de la petición como un String.
     */
    @ModelAttribute("requestURI")
    public String addRequestUriToModel(HttpServletRequest request) {
        return request.getRequestURI();
    }
}