package files.stash.config;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import files.stash.config.value.ConfigValues;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Application-level configuration class for defining and exposing Spring-managed beans.
 * <p>
 * This class includes the configuration of external services, such as Azure Blob Storage,
 * and MyBatis TypeHandlers for proper enum handling.
 */
@Configuration
public class AppConfiguration {

    private final ConfigValues configValues;
    private final SqlSessionFactory sqlSessionFactory;

    /**
     * Constructor for injecting dependencies.
     *
     * @param configValues a component that holds configuration values loaded from properties.
     * @param sqlSessionFactory the MyBatis SqlSessionFactory for registering type handlers.
     */
    public AppConfiguration(ConfigValues configValues, SqlSessionFactory sqlSessionFactory) {
        this.configValues = configValues;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /**
     * Registers MyBatis TypeHandlers for proper enum handling.
     */
    @PostConstruct
    public void registerTypeHandlers() {
        TypeHandlerRegistry registry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
        registry.register(DepartmentTypeHandler.class);
        registry.register(ArtifactTypeTypeHandler.class);
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
