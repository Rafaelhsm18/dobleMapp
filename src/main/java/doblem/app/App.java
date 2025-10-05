package doblem.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Clase principal que inicia toda la aplicación de Spring Boot.
 */
@SpringBootApplication
@EnableJpaAuditing // Habilita la auditoría automática para los campos de fecha y usuario
public class App {

    public static void main(String[] args) {
        // Esta línea es la que arranca el servidor web, busca los controladores,
        // configura la base de datos y pone en marcha toda la aplicación.
        SpringApplication.run(App.class, args);
    }
}