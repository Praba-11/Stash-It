package files.stash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main entry point for the StashIt application.
 * <p>
 * This class bootstraps the Spring Boot application by scanning the base package
 * {@code files.stash} and starting the embedded server.
 */
@SpringBootApplication
@ComponentScan(basePackages = "files.stash")
public class Application {

    /**
     * The main method that launches the Spring Boot application.
     *
     * @param args command-line arguments passed during application startup
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
