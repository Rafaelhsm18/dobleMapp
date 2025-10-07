package doblem.app.repository;

import doblem.app.modelos.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    // Spring Data JPA creará automáticamente la consulta para buscar un usuario por su username
    Optional<Usuarios> findByUsername(String username);
}