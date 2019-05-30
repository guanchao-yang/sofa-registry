package com.alipay.sofa.registry.server.meta.bootstrap.configuration;

import com.alipay.sofa.registry.server.meta.resource.*;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * ResourceConfiguration
 *
 * @author yangguanchao
 * @since 2019/05/30
 */
@Component
public class ResourceConfiguration {

    @Bean
    public ResourceConfig jerseyResourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(JacksonFeature.class);
        return resourceConfig;
    }

    @Bean
    public DecisionModeResource decisionModeResource() {
        return new DecisionModeResource();
    }

    @Bean
    public PersistentDataResource persistentDataResource() {
        return new PersistentDataResource();
    }

    @Bean
    public MetaDigestResource metaDigestResource() {
        return new MetaDigestResource();
    }

    @Bean
    public HealthResource healthResource() {
        return new HealthResource();
    }

    @Bean
    public MetaStoreResource metaStoreResource() {
        return new MetaStoreResource();
    }

    @Bean
    public StopPushDataResource stopPushDataResource() {
        return new StopPushDataResource();
    }
}
