package com.example.service;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * Created by yaswanth on 05/04/19.
 */
@Service
public class RabbitMQListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String messageR = new String(message.getBody());
        System.out.println(messageR);
    }
}
