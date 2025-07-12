package files.stash.config.value;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration holder for application-level properties.
 * <p>
 * This class is used to retrieve and expose configuration values defined in the
 * application's property files (e.g., {@code application.yml} or {@code application.properties}).
 */
@Getter
@Component
public class ConfigValues {

    /**
     * Azure Storage connection string.
     * <p>
     * This value is injected from the application configuration using the key
     * {@code spring.cloud.azure.storage.connection-string}.
     */
    @Value("${spring.cloud.azure.storage.connection-string}")
    private String connectionString;
}
