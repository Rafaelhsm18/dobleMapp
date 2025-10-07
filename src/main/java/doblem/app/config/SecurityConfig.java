/*package doblem.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Usamos BCrypt, el estándar para cifrado de contraseñas
        return new BCryptPasswordEncoder();
    }
}*/

package doblem.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // --- AÑADE ESTE MÉTODO ---
    // Este "Bean" anula la configuración de seguridad por defecto.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Autoriza todas las peticiones HTTP.
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // permite cualquier petición sin autenticación
            )
            // Deshabilita CSRF temporalmente para facilitar el desarrollo con formularios.
            .csrf(csrf -> csrf.disable());

        return http.build();
    }
}