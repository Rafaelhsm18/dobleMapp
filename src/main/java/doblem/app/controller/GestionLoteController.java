package doblem.app.controller;

import doblem.app.modelos.*;
import doblem.app.services.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/produccion/gestionar/{loteId}")
public class GestionLoteController {

    private final LoteProductoService loteService;
    private final RegistroEtapasLoteService registroService;
    private final EtapaMaestroService etapaMaestroService;
    private final EmpleadoService empleadoService;
    private final PlantillaProcesoService plantillaService; // <-- SERVICIO AÑADIDO


    public GestionLoteController(LoteProductoService loteService, RegistroEtapasLoteService registroService, EtapaMaestroService etapaMaestroService, EmpleadoService empleadoService,PlantillaProcesoService plantillaService) {
        this.loteService = loteService;
        this.registroService = registroService;
        this.etapaMaestroService = etapaMaestroService;
        this.empleadoService = empleadoService;
        this.plantillaService = plantillaService; // <-- SERVICIO AÑADIDO
    }

//    @GetMapping
//    public String mostrarGestionLote(@PathVariable Integer loteId, Model model) {
//        LoteProducto lote = loteService.findById(loteId);
//        model.addAttribute("lote", lote);
//        model.addAttribute("registros", registroService.findByLoteId(loteId));
//        model.addAttribute("etapasMaestro", etapaMaestroService.findAll());
//        model.addAttribute("empleados", empleadoService.findAll());
//        return "produccion/gestion-lote";
//    }
    
    @GetMapping
    public String mostrarGestionLote(@PathVariable Integer loteId, Model model) {
        LoteProducto lote = loteService.findById(loteId);
        TipoProducto tipoProducto = lote.getProducto().getTipoProducto();

        // 1. Obtenemos la plantilla para este tipo de producto
        List<PlantillaProceso> plantilla = plantillaService.findByTipoProductoId(tipoProducto.getId());
        
        // 2. Obtenemos los registros que ya existen para este lote
        List<RegistroEtapasLote> registrosExistentes = registroService.findByLoteId(loteId);

        // 3. (Opcional pero recomendado) Creamos una lista de etapas pendientes para el formulario
        List<EtapaMaestro> etapasRegistradasIds = registrosExistentes.stream()
                .map(RegistroEtapasLote::getEtapa)
                .collect(Collectors.toList());
        
        List<PlantillaProceso> pasosPendientes = plantilla.stream()
                .filter(paso -> !etapasRegistradasIds.contains(paso.getEtapa()))
                .collect(Collectors.toList());

        model.addAttribute("lote", lote);
        model.addAttribute("plantilla", plantilla); // La plantilla completa
        model.addAttribute("registros", registrosExistentes); // Los pasos ya hechos
        model.addAttribute("pasosPendientes", pasosPendientes); // Los pasos que faltan
        model.addAttribute("empleados", empleadoService.findAll());
        return "produccion/gestion-lote";
    }

//    @PostMapping("/registrar-etapa")
//    public String registrarEtapa(@PathVariable Integer loteId, @ModelAttribute RegistroEtapasLote registro) {
//        LoteProducto lote = loteService.findById(loteId);
//        registro.setLoteProducto(lote);
//        if (registro.getFechaCompletado() == null) {
//            registro.setFechaCompletado(LocalDateTime.now());
//        }
//        registroService.save(registro);
//        return "redirect:/produccion/gestionar/" + loteId;
//    }
    
    @PostMapping("/registrar-etapa")
    public String registrarEtapa(@PathVariable Integer loteId, @ModelAttribute RegistroEtapasLote registro) {
        LoteProducto lote = loteService.findById(loteId);
        registro.setLoteProducto(lote);
        if (registro.getFechaCompletado() == null) {
            registro.setFechaCompletado(LocalDateTime.now());
        }
        // Aseguramos que el valor del checkbox se guarde como true/false, no nulo
        if (registro.getConfirmado() == null) {
            registro.setConfirmado(false);
        }
        registroService.save(registro);
        return "redirect:/produccion/gestionar/" + loteId;
    }
    
    

    @GetMapping("/borrar-registro/{registroId}")
    public String borrarRegistro(@PathVariable Integer loteId, @PathVariable Integer registroId) {
        registroService.deleteById(registroId);
        return "redirect:/produccion/gestionar/" + loteId;
    }
}