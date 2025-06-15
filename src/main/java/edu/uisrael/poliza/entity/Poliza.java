package edu.uisrael.poliza.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "poliza", schema = "poliza_db")
public class Poliza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPoliza", nullable = false)
    private Integer id;

    @Column(name = "capitali", precision = 10, scale = 2)
    private BigDecimal capitali;

    @Column(name = "montof", precision = 10, scale = 2)
    private BigDecimal montof;

    @Column(name = "tasaInteres", precision = 5, scale = 2)
    private BigDecimal tasaInteres;

    @Column(name = "capitalizacion")
    private Integer capitalizacion;

    @Column(name = "tiempo")
    private Integer tiempo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    private Usuario idUsuario;

}