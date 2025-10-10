package doblem.app.controller;

import doblem.app.modelos.*;
import doblem.app.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller
@RequestMapping("/productos/{productoId}/formula")
public class FormulaController {

    private final ProductoService productoService;
    private final FormulaService formulaService;
    private final MateriaPrimaService materiaPrimaService;

    public FormulaController(ProductoService productoService, FormulaService formulaService, MateriaPrimaService materiaPrimaService) {
        this.productoService = productoService;
        this.formulaService = formulaService;
        this.materiaPrimaService = materiaPrimaService;
    }

    @GetMapping
    public String mostrarFormula(@PathVariable Integer productoId, Model model) {
        Producto producto = productoService.findById(productoId);
        Formula formula = formulaService.findOrCreateFormulaByProducto(producto);

        model.addAttribute("producto", producto);
        model.addAttribute("formula", formula);
        model.addAttribute("materiasPrimas", materiaPrimaService.findAll());
        return "productos/formula";
    }

    @PostMapping("/ingredientes/agregar")
    public String agregarIngrediente(@PathVariable Integer productoId,
                                     @RequestParam Integer materiaPrimaId,
                                     @RequestParam BigDecimal cantidad,
                                     @RequestParam String unidadMedida) {
        
        Producto producto = productoService.findById(productoId);
        Formula formula = formulaService.findOrCreateFormulaByProducto(producto);
        MateriaPrima materiaPrima = materiaPrimaService.findById(materiaPrimaId);

        IngredienteFormula nuevoIngrediente = new IngredienteFormula();
        nuevoIngrediente.setFormula(formula);
        nuevoIngrediente.setMateriaPrima(materiaPrima);
        nuevoIngrediente.setCantidad(cantidad);
        nuevoIngrediente.setUnidadMedida(unidadMedida);
        
        formulaService.saveIngrediente(nuevoIngrediente);

        return "redirect:/productos/" + productoId + "/formula";
    }

    // --- MÉTODO DE BORRADO ACTUALIZADO ---
    @GetMapping("/ingredientes/borrar/{materiaPrimaId}")
    public String borrarIngrediente(@PathVariable Integer productoId, @PathVariable Integer materiaPrimaId) {
        Formula formula = formulaService.findOrCreateFormulaByProducto(productoService.findById(productoId));
        formulaService.deleteIngrediente(formula.getId(), materiaPrimaId);
        return "redirect:/productos/" + productoId + "/formula";
    }

}