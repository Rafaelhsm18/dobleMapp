package doblem.app.controller;

import doblem.app.modelos.MateriaPrima;
import doblem.app.services.MateriaPrimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/materias-primas")
public class MateriaPrimaController {

    private final MateriaPrimaService materiaPrimaService;

    @Autowired
    public MateriaPrimaController(MateriaPrimaService materiaPrimaService) {
        this.materiaPrimaService = materiaPrimaService;
    }

    @GetMapping
    public String listarMateriasPrimas(Model model) {
        List<MateriaPrima> materiasPrimas = materiaPrimaService.findAll();
        model.addAttribute("materiasPrimas", materiasPrimas);
        return "materiasprimas/materias-primas";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("materiaPrima", new MateriaPrima());
        return "materiasprimas/materia-prima-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        MateriaPrima materiaPrima = materiaPrimaService.findById(id);
        model.addAttribute("materiaPrima", materiaPrima);
        return "materiasprimas/materia-prima-form";
    }

    @PostMapping("/guardar")
    public String guardarMateriaPrima(@ModelAttribute MateriaPrima materiaPrima) {
        materiaPrimaService.save(materiaPrima);
        return "redirect:/materias-primas";
    }

    @GetMapping("/borrar/{id}")
    public String borrarMateriaPrima(@PathVariable Integer id) {
        materiaPrimaService.deleteById(id);
        return "redirect:/materias-primas";
    }
}