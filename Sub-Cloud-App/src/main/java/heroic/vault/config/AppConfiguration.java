package heroic.vault.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import heroic.vault.config.value.ConfigValues;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    private final ConfigValues configValues;

    public AppConfiguration(ConfigValues configValues) {
        this.configValues = configValues;
    }

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
                .connectionString(configValues.getConnectionString())
                .buildClient();
    }
}
