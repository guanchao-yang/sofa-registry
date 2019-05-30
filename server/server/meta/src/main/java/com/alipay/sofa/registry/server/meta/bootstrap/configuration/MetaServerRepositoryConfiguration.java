package com.alipay.sofa.registry.server.meta.bootstrap.configuration;

import com.alipay.sofa.registry.server.meta.remoting.RaftExchanger;
import com.alipay.sofa.registry.server.meta.repository.NodeConfirmStatusService;
import com.alipay.sofa.registry.server.meta.repository.RepositoryService;
import com.alipay.sofa.registry.server.meta.repository.VersionRepositoryService;
import com.alipay.sofa.registry.server.meta.repository.annotation.RaftAnnotationBeanPostProcessor;
import com.alipay.sofa.registry.server.meta.repository.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MetaServerRepositoryConfiguration
 *
 * @author yangguanchao
 * @since 2019/05/30
 */
@Component
public class MetaServerRepositoryConfiguration {
    @Bean
    public RepositoryService dataRepositoryService() {
        return new DataRepositoryService();
    }

    @Bean
    public RepositoryService metaRepositoryService() {
        return new MetaRepositoryService();
    }

    @Bean
    public NodeConfirmStatusService dataConfirmStatusService() {
        return new DataConfirmStatusService();
    }

    @Bean
    public RepositoryService sessionRepositoryService() {
        return new SessionRepositoryService();
    }

    @Bean
    public VersionRepositoryService sessionVersionRepositoryService() {
        return new SessionVersionRepositoryService();
    }

    @Bean
    public NodeConfirmStatusService sessionConfirmStatusService() {
        return new SessionConfirmStatusService();
    }

    @Bean
    public RaftExchanger raftExchanger() {
        return new RaftExchanger();
    }

    @Bean
    public RaftAnnotationBeanPostProcessor raftAnnotationBeanPostProcessor() {
        return new RaftAnnotationBeanPostProcessor();
    }
}
