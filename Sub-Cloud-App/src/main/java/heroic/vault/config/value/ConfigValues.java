package heroic.vault.config.value;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConfigValues {

    @Value("${spring.cloud.azure.storage.connection-string}")
    private String connectionString;
}
