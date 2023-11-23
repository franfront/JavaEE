package org.ffernandez.hibernateapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes") // nombre de la tabla en la base de datos
public class Cliente {

    @Id // indica que es una llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que es autoincremental
    private Long id;
    private String nombre;
    private String apellido;
    private String formaPago;

}
