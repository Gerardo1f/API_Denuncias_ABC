package com.denuncias.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "denuncias")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String empresa;

    @Column(nullable = false, length = 50)
    private String pais;

    @Column(nullable = false, length = 100)
    private String estado;

    @Column(nullable = false)
    private Integer centro;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String detalle;

    @Column(name = "fecha_hechos", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaHechos;

    @Column(nullable = false, length = 5)
    private String folio;

    @ManyToOne
    @JoinColumn(name = "estatus_id", nullable = false)
    private Estatus estatus;

    @Column(name = "nombre_completo", length = 255)
    private String nombreCompleto;

    @Column(length = 255)
    private String correo;

    @Column(length = 50)
    private String telefono;

    @Column(nullable = false, length = 255)
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "denuncia", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comentario> comentarios;

    // Getters y Setters
}