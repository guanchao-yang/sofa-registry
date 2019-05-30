/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
