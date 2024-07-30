package com.example.clientemicroservice.controller;

import com.example.clientemicroservice.model.Cliente;
import com.example.clientemicroservice.service.ClienteService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = clienteService.save(cliente);
        rabbitTemplate.convertAndSend("clientExchange", "clientRoutingKey", savedCliente);
        return savedCliente;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        if (cliente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.save(cliente);
    }

    @PatchMapping("/{id}")
    public Cliente patchCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente existingCliente = clienteService.findById(id);
        if (cliente.getContraseña() != null) {
            existingCliente.setContraseña(cliente.getContraseña());
        }
        if (cliente.getEstado() != null) {
            existingCliente.setEstado(cliente.getEstado());
        }
        return clienteService.save(existingCliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
    }
}
