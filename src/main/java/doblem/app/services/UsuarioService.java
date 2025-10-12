package doblem.app.services;

import doblem.app.modelos.Usuarios;
import doblem.app.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuarios> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuarios findById(Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void save(Usuarios usuario) {
        // Ciframos la contraseña solo si es nueva o ha cambiado
        // (una contraseña ya cifrada tiene un formato como $2a$10$...)
        if (usuario.getPassword() != null && !usuario.getPassword().startsWith("$2a$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        usuarioRepository.save(usuario);
    }

    public void deleteById(Integer id) {
        usuarioRepository.deleteById(id);
    }
}