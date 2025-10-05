package doblem.app.modelos;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Integer id;
    private String username;
    private String password;
    private Boolean activo;

    @OneToOne
    @JoinColumn(name = "ID_Empleado", referencedColumnName = "ID_Empleado")
    private Empleados empleado;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "Usuarios_Roles",
        joinColumns = @JoinColumn(name = "ID_Usuario"),
        inverseJoinColumns = @JoinColumn(name = "ID_Rol")
    )
    private Set<Roles> roles;
}
