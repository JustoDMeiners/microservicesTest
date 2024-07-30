package com.example.clientemicroservice;

import com.example.clientemicroservice.model.Cliente;
import com.example.clientemicroservice.repository.ClienteRepository;
import com.example.clientemicroservice.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClienteMicroserviceApplicationTests {


    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    public void ClienteServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        when(clienteRepository.findById(anyLong())).thenReturn(Optional.of(cliente));

        Cliente found = clienteService.findById(1L);

        assertEquals(cliente.getId(), found.getId());
    }

    @Test
    void testSaveCliente() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente saved = clienteService.save(cliente);

        assertEquals(cliente.getId(), saved.getId());
    }

}
