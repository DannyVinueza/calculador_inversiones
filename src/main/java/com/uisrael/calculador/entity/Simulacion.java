package com.uisrael.calculador.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "simulaciones")
@AllArgsConstructor
@NoArgsConstructor
public class Simulacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombres;
    private String cedula;
    private String email;
    private String celular;
    private double capitalInicial;
    private int numeroPeriodos;
    private int tiempoAnios;

    private double tasaInteres;
    private double valorFuturo;
    private double interesGanado;
    private int categoria;
    private LocalDateTime fechaCreacion;
}
