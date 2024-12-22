/**
 * Copyright (C) Appranix, Inc - All Rights Reserved.
 *
 * <p>Unauthorized copying of this file, via any medium is strictly prohibited.
 *
 * <p>Proprietary and confidential.
 */
package heroic.vault.dao;

import heroic.vault.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DAO {

    @Autowired
    private Mapper mapper;

    public String getInfo(Long roll) {
        return mapper.getInfo(roll);
    }
}
