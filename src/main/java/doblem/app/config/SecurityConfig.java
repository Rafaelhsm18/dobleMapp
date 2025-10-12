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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // --- AÑADE ESTE MÉTODO ---
//    // Este "Bean" anula la configuración de seguridad por defecto.
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // Autoriza todas las peticiones HTTP.
//            .authorizeHttpRequests(auth -> auth
//                .anyRequest().permitAll() // permite cualquier petición sin autenticación
//            )
//            // Deshabilita CSRF temporalmente para facilitar el desarrollo con formularios.
//            .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
    
    
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Permite el acceso a recursos estáticos (CSS, JS, imágenes) y a la página de login sin autenticación
                .requestMatchers("/css/**", "/js/**", "/images/**", "/login").permitAll()
                // Define que las rutas de gestión de usuarios y roles solo son para el admin
                .requestMatchers("/usuarios/**", "/roles/**").hasRole("ADMIN")
                // Cualquier otra petición requiere que el usuario esté autenticado
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                // URL de la página de login personalizada
                .loginPage("/login")
                // URL a la que se envía el formulario de login
                .loginProcessingUrl("/login")
                // URL a la que se redirige tras un login exitoso
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                // Invalida la sesión y limpia la autenticación
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                // URL para activar el logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // URL a la que se redirige tras el logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Temporalmente deshabilitado

        return http.build();
    }
}