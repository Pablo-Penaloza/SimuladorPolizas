package edu.uisrael.poliza.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuario", schema = "poliza_db")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Size(max = 100)
    @Column(name = "apellido", length = 100)
    private String apellido;

    @Size(max = 15)
    @Column(name = "cedula", length = 15)
    private String cedula;

    @Size(max = 200)
    @Column(name = "correo", length = 200)
    private String correo;

    @Size(max = 20)
    @Column(name = "celular", length = 20)
    private String celular;

}