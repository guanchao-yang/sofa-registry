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

import com.alipay.sofa.registry.remoting.bolt.exchange.BoltExchange;
import com.alipay.sofa.registry.remoting.exchange.Exchange;
import com.alipay.sofa.registry.remoting.exchange.NodeExchanger;
import com.alipay.sofa.registry.remoting.jersey.exchange.JerseyExchange;
import com.alipay.sofa.registry.server.meta.remoting.DataNodeExchanger;
import com.alipay.sofa.registry.server.meta.remoting.MetaClientExchanger;
import com.alipay.sofa.registry.server.meta.remoting.MetaServerExchanger;
import com.alipay.sofa.registry.server.meta.remoting.SessionNodeExchanger;
import com.alipay.sofa.registry.server.meta.remoting.connection.DataConnectionHandler;
import com.alipay.sofa.registry.server.meta.remoting.connection.MetaConnectionHandler;
import com.alipay.sofa.registry.server.meta.remoting.connection.SessionConnectionHandler;
import com.alipay.sofa.registry.server.meta.remoting.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * MetaServerRemotingConfiguration
 *
 * @author yangguanchao
 * @since 2019/05/30
 */

@Component
public class MetaServerRemotingConfiguration {

    @Bean
    public Exchange boltExchange() {
        return new BoltExchange();
    }

    @Bean
    public Exchange jerseyExchange() {
        return new JerseyExchange();
    }

    @Bean(name = "sessionServerHandlers")
    public Collection<AbstractServerHandler> sessionServerHandlers() {
        Collection<AbstractServerHandler> list = new ArrayList<>();
        list.add(sessionConnectionHandler());
        list.add(sessionNodeHandler());
        list.add(reNewNodesRequestHandler());
        list.add(getNodesRequestHandler());
        list.add(fetchProvideDataRequestHandler());
        return list;
    }

    @Bean(name = "dataServerHandlers")
    public Collection<AbstractServerHandler> dataServerHandlers() {
        Collection<AbstractServerHandler> list = new ArrayList<>();
        list.add(dataConnectionHandler());
        list.add(getNodesRequestHandler());
        list.add(dataNodeHandler());
        list.add(reNewNodesRequestHandler());
        return list;
    }

    @Bean(name = "metaServerHandlers")
    public Collection<AbstractServerHandler> metaServerHandlers() {
        Collection<AbstractServerHandler> list = new ArrayList<>();
        list.add(metaConnectionHandler());
        list.add(getChangeListRequestHandler());
        list.add(getNodesRequestHandler());
        return list;
    }

    @Bean
    public AbstractServerHandler sessionConnectionHandler() {
        return new SessionConnectionHandler();
    }

    @Bean
    public AbstractServerHandler dataConnectionHandler() {
        return new DataConnectionHandler();
    }

    @Bean
    public AbstractServerHandler metaConnectionHandler() {
        return new MetaConnectionHandler();
    }

    @Bean
    public AbstractServerHandler getChangeListRequestHandler() {
        return new GetChangeListRequestHandler();
    }

    @Bean
    public AbstractServerHandler getNodesRequestHandler() {
        return new GetNodesRequestHandler();
    }

    @Bean
    public AbstractServerHandler sessionNodeHandler() {
        return new SessionNodeHandler();
    }

    @Bean
    public AbstractServerHandler reNewNodesRequestHandler() {
        return new ReNewNodesRequestHandler();
    }

    @Bean
    public AbstractServerHandler dataNodeHandler() {
        return new DataNodeHandler();
    }

    @Bean
    public AbstractServerHandler fetchProvideDataRequestHandler() {
        return new FetchProvideDataRequestHandler();
    }

    @Bean
    public NodeExchanger sessionNodeExchanger() {
        return new SessionNodeExchanger();
    }

    @Bean
    public NodeExchanger dataNodeExchanger() {
        return new DataNodeExchanger();
    }

    @Bean
    public NodeExchanger metaServerExchanger() {
        return new MetaServerExchanger();
    }

    @Bean
    public MetaClientExchanger metaClientExchanger() {
        return new MetaClientExchanger();
    }
}