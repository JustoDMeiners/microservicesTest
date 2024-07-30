package com.example.clientemicroservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("CLIENTE")
public class Cliente extends Persona{

    private String contraseña;
    private String estado;

}
