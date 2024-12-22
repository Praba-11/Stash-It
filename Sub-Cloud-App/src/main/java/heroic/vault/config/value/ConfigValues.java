/**
 * Copyright (C) Appranix, Inc - All Rights Reserved.
 *
 * <p>Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * <p>Proprietary and confidential.
 */
package heroic.vault.config.value;

import org.springframework.beans.factory.annotation.Value;

public class ConfigValues {

    @Value("${azure.storage.blob.connection-string}")
    private String connectionString;
}
