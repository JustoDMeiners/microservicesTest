package com.example.clientemicroservice.service;

import com.example.clientemicroservice.model.Persona;
import com.example.clientemicroservice.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> findAll() {
        return personaRepository.findAll();
    }
    public Persona findById(Long id) {
        return personaRepository.findById(id).get();
    }
    public Persona save(Persona persona) {
        return personaRepository.save(persona);
    }
    public void deleteById(Long id) {
        personaRepository.deleteById(id);
    }
}
