package files.stash.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import files.stash.config.value.ConfigValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Application-level configuration class for defining and exposing Spring-managed beans.
 * <p>
 * This class includes the configuration of external services, such as Azure Blob Storage.
 */
@Configuration
public class AppConfiguration {

    private final ConfigValues configValues;

    /**
     * Constructor for injecting {@link ConfigValues} dependency.
     *
     * @param configValues a component that holds configuration values loaded from properties.
     */
    public AppConfiguration(ConfigValues configValues) {
        this.configValues = configValues;
    }

    /**
     * Creates and configures a {@link BlobServiceClient} bean for interacting with Azure Blob Storage.
     *
     * @return a configured instance of {@link BlobServiceClient}.
     */
    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(configValues.getConnectionString())
                .buildClient();
    }
}
