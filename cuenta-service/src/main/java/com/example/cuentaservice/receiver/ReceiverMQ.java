package com.example.cuentaservice.receiver;

import com.example.cuentaservice.configuration.RabbitMQConfig;
import com.example.cuentaservice.model.Cliente;
import com.example.cuentaservice.service.CuentaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReceiverMQ {
    @Autowired
    private CuentaService cuentaService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(Cliente cliente) {
        System.out.println("Received message: " + cliente);
    }

}
