package doblem.app.controller;

import doblem.app.services.StockProductoTerminadoService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class StockController {

    private final StockProductoTerminadoService stockService;

    public StockController(StockProductoTerminadoService stockService) {
        this.stockService = stockService;
    }

    @GetMapping
    public String verStock(Model model, @PageableDefault(size = 7) Pageable pageable) { // Paginación por defecto de 7 elementos
        model.addAttribute("stockPage", stockService.findAll(pageable));
        model.addAttribute("resumenStock", stockService.getResumenStock());
        return "stock/stock";
    }
}