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