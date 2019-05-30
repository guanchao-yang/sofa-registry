package com.alipay.sofa.registry.server.meta.bootstrap.configuration;

import com.alipay.sofa.registry.server.meta.bootstrap.MetaServerConfig;
import com.alipay.sofa.registry.server.meta.executor.ExecutorManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ExecutorConfiguation
 *
 * @author yangguanchao
 * @since 2019/05/30
 */
@Component
public class ExecutorConfiguation {

    @Bean
    public ExecutorManager executorManager(MetaServerConfig metaServerConfig) {
        return new ExecutorManager(metaServerConfig);
    }

}