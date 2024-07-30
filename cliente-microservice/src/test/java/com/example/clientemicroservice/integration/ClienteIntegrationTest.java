package com.example.clientemicroservice.integration;

import com.example.clientemicroservice.model.Cliente;
import com.example.clientemicroservice.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ClienteIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        Cliente cliente = new Cliente();
        cliente.setNombre("Test Name");
        cliente.setDireccion("maipu 354");
        cliente.setContraseña("1234");
        cliente.setEdad(25);
        clienteRepository.save(cliente);
    }

    @Test
    void testGetClienteById() throws Exception {
        mockMvc.perform(get("/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json("{\"id\":1,\"nombre\":\"Test Name\",\"genero\":null,\"edad\":25,\"identificacion\":null,\"direccion\":\"maipu 354\",\"telefono\":null,\"estado\":null,\"contraseña\":\"1234\"}"));
    }
}
