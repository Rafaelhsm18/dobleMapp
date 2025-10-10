package doblem.app.controller;

import doblem.app.modelos.*;
import doblem.app.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tipos-producto/{tipoProductoId}/plantilla")
public class PlantillaProcesoController {

    private final PlantillaProcesoService plantillaService;
    private final TipoProductoService tipoProductoService;
    private final EtapaMaestroService etapaMaestroService;

    public PlantillaProcesoController(PlantillaProcesoService plantillaService, TipoProductoService tipoProductoService, EtapaMaestroService etapaMaestroService) {
        this.plantillaService = plantillaService;
        this.tipoProductoService = tipoProductoService;
        this.etapaMaestroService = etapaMaestroService;
    }

    @GetMapping
    public String mostrarPlantilla(@PathVariable Integer tipoProductoId, Model model) {
        TipoProducto tipoProducto = tipoProductoService.findById(tipoProductoId);
        model.addAttribute("tipoProducto", tipoProducto);
        model.addAttribute("plantilla", plantillaService.findByTipoProductoId(tipoProductoId));
        model.addAttribute("etapasMaestro", etapaMaestroService.findAll());
        return "tiposproducto/plantilla-proceso";
    }

    @PostMapping("/agregar-etapa")
    public String agregarEtapa(@PathVariable Integer tipoProductoId,
                               @RequestParam Integer etapaId,
                               @RequestParam int orden) {
        
        TipoProducto tipoProducto = tipoProductoService.findById(tipoProductoId);
        EtapaMaestro etapa = etapaMaestroService.findById(etapaId);

        PlantillaProceso nuevoPaso = new PlantillaProceso();
        nuevoPaso.setTipoProducto(tipoProducto);
        nuevoPaso.setEtapa(etapa);
        nuevoPaso.setOrden(orden);
        
        plantillaService.save(nuevoPaso);
        
        return "redirect:/tipos-producto/" + tipoProductoId + "/plantilla";
    }

    @GetMapping("/borrar-etapa/{etapaId}")
    public String borrarEtapa(@PathVariable Integer tipoProductoId, @PathVariable Integer etapaId) {
        TipoProducto tipoProducto = tipoProductoService.findById(tipoProductoId);
        EtapaMaestro etapa = etapaMaestroService.findById(etapaId);
        
        PlantillaProceso pasoABorrar = new PlantillaProceso();
        pasoABorrar.setTipoProducto(tipoProducto);
        pasoABorrar.setEtapa(etapa);
        
        plantillaService.delete(pasoABorrar);

        return "redirect:/tipos-producto/" + tipoProductoId + "/plantilla";
    }
}