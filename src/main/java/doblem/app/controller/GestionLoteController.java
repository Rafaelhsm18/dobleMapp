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
    private final PlantillaProcesoService plantillaService;
    private final IncidenciaLoteService incidenciaService; // <-- NUEVO SERVICIO AÑADIDO


    public GestionLoteController(LoteProductoService loteService, RegistroEtapasLoteService registroService, EtapaMaestroService etapaMaestroService, EmpleadoService empleadoService,PlantillaProcesoService plantillaService, IncidenciaLoteService incidenciaService) { // <-- NUEVO SERVICIO EN CONSTRUCTOR
        this.loteService = loteService;
        this.registroService = registroService;
        this.etapaMaestroService = etapaMaestroService;
        this.empleadoService = empleadoService;
        this.plantillaService = plantillaService;
        this.incidenciaService = incidenciaService; // <-- ASIGNACIÓN
    }
    
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
        model.addAttribute("incidencias", incidenciaService.findByLoteId(loteId)); // <-- INCIDENCIAS
        model.addAttribute("nuevaIncidencia", new IncidenciaLote()); // <-- OBJETO PARA EL FORMULARIO
        
        return "produccion/gestion-lote";
    }

    @PostMapping("/registrar-etapa")
    public String registrarEtapa(@PathVariable Integer loteId,
                                 @RequestParam("etapa") Integer etapaId,
                                 @RequestParam(name = "empleado", required = false) Integer empleadoId,
                                 @RequestParam("observaciones") String observaciones,
                                 @RequestParam(name = "confirmado", required = false) Boolean confirmado) {

        // 1. Creamos el objeto vacío
        RegistroEtapasLote registro = new RegistroEtapasLote();

        // 2. Buscamos las entidades completas usando los IDs
        LoteProducto lote = loteService.findById(loteId);
        EtapaMaestro etapa = etapaMaestroService.findById(etapaId);
        if (empleadoId != null) {
            Empleados empleado = empleadoService.findById(empleadoId);
            registro.setEmpleado(empleado);
        }

        // 3. Asignamos todos los valores al objeto
        registro.setLoteProducto(lote);
        registro.setEtapa(etapa);
        registro.setObservaciones(observaciones);
        registro.setConfirmado(confirmado != null && confirmado);
        registro.setFechaCompletado(LocalDateTime.now());

        // 4. Guardamos el objeto ya completo
        registroService.save(registro);

        // 5. Actualizamos el estado del lote padre
        loteService.actualizarEstadoLote(loteId);

        return "redirect:/produccion/gestionar/" + loteId;
    }

    @GetMapping("/borrar-registro/{registroId}")
    public String borrarRegistro(@PathVariable Integer loteId, @PathVariable Integer registroId) {
        registroService.deleteById(registroId);
        loteService.actualizarEstadoLote(loteId);
        return "redirect:/produccion/gestionar/" + loteId;
    }

    // --- NUEVOS MÉTODOS PARA INCIDENCIAS ---

    @PostMapping("/registrar-incidencia")
    public String registrarIncidencia(@PathVariable Integer loteId, 
                                      @ModelAttribute IncidenciaLote incidencia,
                                      @RequestParam(name = "empleadoReportaId", required = false) Integer empleadoReportaId) {
        
        LoteProducto lote = loteService.findById(loteId);
        incidencia.setLoteProducto(lote);
        incidencia.setFechaReporte(LocalDateTime.now());
        
        if (empleadoReportaId != null) {
            Empleados empleado = empleadoService.findById(empleadoReportaId);
            incidencia.setEmpleadoReporta(empleado);
        }
        
        incidencia.setResuelta(false); // Asegurar que siempre empieza sin resolver
        incidenciaService.save(incidencia);
        
        return "redirect:/produccion/gestionar/" + loteId;
    }
    
    @GetMapping("/resolver-incidencia/{incidenciaId}")
    public String resolverIncidencia(@PathVariable Integer loteId, @PathVariable Integer incidenciaId) {
        incidenciaService.marcarComoResuelta(incidenciaId);
        return "redirect:/produccion/gestionar/" + loteId;
    }

    @GetMapping("/borrar-incidencia/{incidenciaId}")
    public String borrarIncidencia(@PathVariable Integer loteId, @PathVariable Integer incidenciaId) {
        incidenciaService.deleteById(incidenciaId);
        return "redirect:/produccion/gestionar/" + loteId;
    }
}