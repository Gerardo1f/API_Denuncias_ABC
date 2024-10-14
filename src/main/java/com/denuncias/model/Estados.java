package com.denuncias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.List;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Estados")
public class Estados
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "idPais", nullable = false) // El campo no puede ser nulo
    private Long idPais;




    public Estados() {
    }

}
