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
