package com.example.config;

import com.example.service.RabbitMQListener;
import com.example.util.PropertyUtil;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yaswanth on 05/04/19.
 */
@Configuration
public class RabbitMQConfig {
    @Autowired
    private PropertyUtil propUtil;

    @Autowired
    private RabbitMQListener listener;

    public ConnectionFactory getConnection() throws Exception{
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(propUtil.getProperty("rabbitmq.user"));
        connectionFactory.setPassword(propUtil.getProperty("rabbitmq.password"));
        connectionFactory.setAddresses(propUtil.getProperty("rabbitmq.host"));
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() throws Exception {
        return new RabbitAdmin(getConnection());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() throws Exception {
        return new RabbitTemplate(getConnection());
    }

    @Bean
    public Queue queue() throws Exception{
        Queue queue = new Queue(propUtil.getProperty("rabbitmq.queue"));
        queue.isDurable();
        return queue;
    }

    @Bean
    public SimpleMessageListenerContainer messageListerner() throws Exception {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(getConnection());
        container.setAcknowledgeMode(AcknowledgeMode.NONE);
        container.setQueueNames(queue().getName());
        container.setConcurrentConsumers(2);
        container.setMessageListener(listener);
        return container;
    }
}
