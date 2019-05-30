package com.alipay.sofa.registry.server.meta.bootstrap.configuration;

import com.alipay.sofa.registry.server.meta.bootstrap.MetaServerConfig;
import com.alipay.sofa.registry.server.meta.bootstrap.MetaServerConfigBean;
import com.alipay.sofa.registry.server.meta.bootstrap.NodeConfig;
import com.alipay.sofa.registry.server.meta.bootstrap.NodeConfigBeanProperty;
import com.alipay.sofa.registry.util.PropertySplitter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MetaServerConfigBeanConfiguration
 *
 * @author yangguanchao
 * @since 2019/05/30
 */
@Component
public class MetaServerConfigBeanConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public MetaServerConfig metaServerConfig() {
        return new MetaServerConfigBean();
    }

    @Bean
    public NodeConfig nodeConfig() {
        return new NodeConfigBeanProperty();
    }

    @Bean(name = "PropertySplitter")
    public PropertySplitter propertySplitter() {
        return new PropertySplitter();
    }
}