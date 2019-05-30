package com.alipay.sofa.registry.server.meta.bootstrap.configuration;

import com.alipay.sofa.registry.jraft.service.PersistenceDataDBService;
import com.alipay.sofa.registry.store.api.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MetaDBConfiguration
 *
 * @author yangguanchao
 * @since 2019/05/30
 */
@Component
public class MetaDBConfiguration {
    @Bean
    public DBService persistenceDataDBService() {
        return new PersistenceDataDBService();
    }
}
