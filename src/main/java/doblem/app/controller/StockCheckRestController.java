package doblem.app.controller;

import doblem.app.modelos.Producto;
import doblem.app.services.ProductoService;
import doblem.app.services.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stock")
public class StockCheckRestController {

    private final StockService stockService;
    private final ProductoService productoService;

    public StockCheckRestController(StockService stockService, ProductoService productoService) {
        this.stockService = stockService;
        this.productoService = productoService;
    }

    @GetMapping("/verificar")
    public ResponseEntity<List<Map<String, Object>>> verificarStock(
            @RequestParam Integer productoId,
            @RequestParam BigDecimal cantidad) {
        
        Producto producto = productoService.findById(productoId);
        if (producto == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Map<String, Object>> resultado = stockService.verificarStockParaLote(producto, cantidad);
        return ResponseEntity.ok(resultado);
    }
}