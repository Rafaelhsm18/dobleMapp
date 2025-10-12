package doblem.app.modelos.dto;

import doblem.app.modelos.Producto;
import java.math.BigDecimal;

public class StockResumenDTO {
    private Producto producto;
    private BigDecimal cantidadTotal;

    public StockResumenDTO(Producto producto, BigDecimal cantidadTotal) {
        this.producto = producto;
        this.cantidadTotal = cantidadTotal;
    }

    // Getters y Setters
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public BigDecimal getCantidadTotal() { return cantidadTotal; }
    public void setCantidadTotal(BigDecimal cantidadTotal) { this.cantidadTotal = cantidadTotal; }
}