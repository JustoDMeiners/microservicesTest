package com.example.cuentaservice.service;

import com.example.cuentaservice.exception.ClienteNotFoundException;
import com.example.cuentaservice.model.Cuenta;
import com.example.cuentaservice.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private RestTemplate restTemplate;


    public Cuenta createCuenta(Cuenta cuenta) {
        // Verificar si el clientId existe
        String clienteServiceUrl = "http://cliente-service:8089/clientes/" + cuenta.getClienteId();
        try {
            restTemplate.getForObject(clienteServiceUrl, Void.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new ClienteNotFoundException("Cliente con ID " + cuenta.getClienteId() + " no encontrado");
        } catch (HttpClientErrorException e) {
            throw new ClienteNotFoundException("Error al verificar el cliente con ID " + cuenta.getClienteId());
        }

        return cuentaRepository.save(cuenta);
    }

    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        cuentaRepository.deleteById(id);
    }
}
