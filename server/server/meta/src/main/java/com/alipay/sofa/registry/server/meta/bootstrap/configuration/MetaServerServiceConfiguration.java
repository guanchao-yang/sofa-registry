package com.alipay.sofa.registry.server.meta.bootstrap.configuration;

import com.alipay.sofa.registry.server.meta.bootstrap.ServiceFactory;
import com.alipay.sofa.registry.server.meta.node.NodeService;
import com.alipay.sofa.registry.server.meta.node.impl.DataNodeServiceImpl;
import com.alipay.sofa.registry.server.meta.node.impl.MetaNodeServiceImpl;
import com.alipay.sofa.registry.server.meta.node.impl.SessionNodeServiceImpl;
import com.alipay.sofa.registry.server.meta.registry.MetaServerRegistry;
import com.alipay.sofa.registry.server.meta.registry.Registry;
import com.alipay.sofa.registry.server.meta.store.DataStoreService;
import com.alipay.sofa.registry.server.meta.store.MetaStoreService;
import com.alipay.sofa.registry.server.meta.store.SessionStoreService;
import com.alipay.sofa.registry.server.meta.store.StoreService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MetaServerServiceConfiguration
 *
 * @author yangguanchao
 * @since 2019/05/30
 */

@Component
public class MetaServerServiceConfiguration {

    @Bean
    public Registry metaServerRegistry() {
        return new MetaServerRegistry();
    }

    @Bean
    public NodeService sessionNodeService() {
        return new SessionNodeServiceImpl();
    }

    @Bean
    public NodeService dataNodeService() {
        return new DataNodeServiceImpl();
    }

    @Bean
    public NodeService metaNodeService() {
        return new MetaNodeServiceImpl();
    }

    @Bean
    public ServiceFactory storeServiceFactory() {
        return new ServiceFactory();
    }

    @Bean
    public StoreService sessionStoreService() {
        return new SessionStoreService();
    }

    @Bean
    public StoreService dataStoreService() {
        return new DataStoreService();
    }

    @Bean
    public StoreService metaStoreService() {
        return new MetaStoreService();
    }

}

