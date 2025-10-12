package doblem.app.repository;

import doblem.app.modelos.LoteProveedor;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoteProveedorRepository extends JpaRepository<LoteProveedor, Integer> {
	
	 // --- MÉTODO NUEVO ---
    // Suma la cantidad recibida de todos los lotes para una materia prima específica
    @Query("SELECT SUM(lp.cantidadRecibida) FROM LoteProveedor lp WHERE lp.materiaPrima.id = :materiaPrimaId")
    BigDecimal sumCantidadRecibidaByMateriaPrimaId(Integer materiaPrimaId);
}