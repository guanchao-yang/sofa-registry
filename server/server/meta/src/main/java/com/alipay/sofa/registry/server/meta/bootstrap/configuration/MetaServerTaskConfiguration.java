package com.alipay.sofa.registry.server.meta.bootstrap.configuration;

import com.alipay.sofa.registry.server.meta.listener.DataNodeChangePushTaskListener;
import com.alipay.sofa.registry.server.meta.listener.PersistenceDataChangeNotifyTaskListener;
import com.alipay.sofa.registry.server.meta.listener.ReceiveStatusConfirmNotifyTaskListener;
import com.alipay.sofa.registry.server.meta.listener.SessionNodeChangePushTaskListener;
import com.alipay.sofa.registry.server.meta.task.processor.DataNodeSingleTaskProcessor;
import com.alipay.sofa.registry.server.meta.task.processor.MetaNodeSingleTaskProcessor;
import com.alipay.sofa.registry.server.meta.task.processor.SessionNodeSingleTaskProcessor;
import com.alipay.sofa.registry.task.batcher.TaskProcessor;
import com.alipay.sofa.registry.task.listener.DefaultTaskListenerManager;
import com.alipay.sofa.registry.task.listener.TaskListener;
import com.alipay.sofa.registry.task.listener.TaskListenerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * MetaServerTaskConfiguration
 *
 * @author yangguanchao
 * @since 2019/05/30
 */
@Component
public class MetaServerTaskConfiguration {

    @Bean
    public TaskProcessor dataNodeSingleTaskProcessor() {
        return new DataNodeSingleTaskProcessor();
    }

    @Bean
    public TaskProcessor metaNodeSingleTaskProcessor() {
        return new MetaNodeSingleTaskProcessor();
    }

    @Bean
    public TaskProcessor sessionNodeSingleTaskProcessor() {
        return new SessionNodeSingleTaskProcessor();
    }

    @Bean
    public TaskListener sessionNodeChangePushTaskListener(TaskListenerManager taskListenerManager) {
        TaskListener taskListener = new SessionNodeChangePushTaskListener(
                sessionNodeSingleTaskProcessor());
        taskListenerManager.addTaskListener(taskListener);
        return taskListener;
    }

    @Bean
    public TaskListener dataNodeChangePushTaskListener(TaskListenerManager taskListenerManager) {
        TaskListener taskListener = new DataNodeChangePushTaskListener(
                dataNodeSingleTaskProcessor());
        taskListenerManager.addTaskListener(taskListener);
        return taskListener;
    }

    @Bean
    public TaskListener receiveStatusConfirmNotifyTaskListener(TaskListenerManager taskListenerManager) {
        TaskListener taskListener = new ReceiveStatusConfirmNotifyTaskListener(
                dataNodeSingleTaskProcessor());
        taskListenerManager.addTaskListener(taskListener);
        return taskListener;
    }

    @Bean
    public TaskListener persistenceDataChangeNotifyTaskListener(TaskListenerManager taskListenerManager) {
        TaskListener taskListener = new PersistenceDataChangeNotifyTaskListener(
                sessionNodeSingleTaskProcessor());
        taskListenerManager.addTaskListener(taskListener);
        return taskListener;
    }

    @Bean
    public TaskListenerManager taskListenerManager() {
        return new DefaultTaskListenerManager();
    }
}
