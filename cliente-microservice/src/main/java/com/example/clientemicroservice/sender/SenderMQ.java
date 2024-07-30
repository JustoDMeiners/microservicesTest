package com.example.clientemicroservice.sender;

import com.example.clientemicroservice.configuration.RabbitMQConfig;
import com.example.clientemicroservice.model.Cliente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SenderMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Cliente cliente) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, cliente);
    }
}
