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
package com.alipay.sofa.registry.server.meta;

import com.alipay.sofa.registry.log.Logger;
import com.alipay.sofa.registry.log.LoggerFactory;
import com.alipay.sofa.registry.server.meta.bootstrap.EnableMetaServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Constructor;

/**
 *
 * @author zhuoyu.sjw
 * @version $Id: MetaApplication.java, v 0.1 2017-11-13 19:03 zhuoyu.sjw Exp $$
 */
@EnableMetaServer
@SpringBootApplication(proxyBeanMethods = false)
public class MetaApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaApplication.class);

    public static void main(String[] args) {
        Constructor<?>[] constructors = java.sql.Date.class.getDeclaredConstructors();
        if (constructors.length <= 0) {
            System.err.println("java.sql.Date no Constructors");
        }
        for (int i = 0; i < constructors.length; i++) {
            System.err.println("java.sql.Date Constructor size=" + constructors.length +
                    " and current index=" + i + " is " + constructors[i]);
        }
        System.out.println("runnning main step 1");
        // setup DefaultUncaughtExceptionHandler
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            LOGGER.error(String.format("UncaughtException in Thread(%s): %s", t.getName(), e.getMessage()), e);
        });

        SpringApplication.run(MetaApplication.class, args);
    }
}
