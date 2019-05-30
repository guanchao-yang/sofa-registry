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
