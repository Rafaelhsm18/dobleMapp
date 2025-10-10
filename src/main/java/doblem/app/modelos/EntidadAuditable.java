package doblem.app.modelos;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntidadAuditable {

	   @CreatedBy
	    @Column(name = "creado_por", updatable = false)
	    private String creadoPor;

	    @CreatedDate
	    @Column(name = "fecha_creacion", updatable = false)
	    private LocalDateTime fechaCreacion;

	    @LastModifiedBy
	    @Column(name = "modificado_por")
	    private String modificadoPor;

	    @LastModifiedDate
	    @Column(name = "fecha_modificacion")
	    private LocalDateTime fechaModificacion;
	    
	    
	    public String getCreadoPor() {
	        return creadoPor;
	    }

	    public void setCreadoPor(String creadoPor) {
	        this.creadoPor = creadoPor;
	    }

	    public LocalDateTime getFechaCreacion() {
	        return fechaCreacion;
	    }

	    public void setFechaCreacion(LocalDateTime fechaCreacion) {
	        this.fechaCreacion = fechaCreacion;
	    }

	    public String getModificadoPor() {
	        return modificadoPor;
	    }

	    public void setModificadoPor(String modificadoPor) {
	        this.modificadoPor = modificadoPor;
	    }

	    public LocalDateTime getFechaModificacion() {
	        return fechaModificacion;
	    }

	    public void setFechaModificacion(LocalDateTime fechaModificacion) {
	        this.fechaModificacion = fechaModificacion;
	    }
	}
