package org.ffernandez.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes") // nombre de la tabla en la base de datos
public class Cliente {

    @Id // indica que es una llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que es autoincremental
    private Long id;
    private String nombre;
    private String apellido;
    @Column(name="forma_pago")
    private String formaPago;

    @Embedded // conecta con la clase Embeddable
    private Auditoria audit = new Auditoria();

    public Cliente() {
    }

    public Cliente(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }



    @PreRemove // se ejecuta antes de eliminar
    public void preRemove() {
        System.out.println("Inicializar algo antes de eliminar");
    }

    @PostPersist // se ejecuta despues de persistir
    public void postPersist() {
        System.out.println("Algo despues de persistir");
    }

    @PostUpdate // se ejecuta despues de actualizar
    public void postUpdate() {
        System.out.println("Algo despues de actualizar");
    }

    @PostRemove // se ejecuta despues de eliminar
    public void postRemove() {
        System.out.println("Algo despues de eliminar");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }



    @Override
    public String toString() {

        LocalDateTime creado = this.audit != null ? this.audit.getCreadoEn() : null;
        LocalDateTime editado = this.audit != null ? this.audit.getEditadoEn() : null;



        return "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago=" + formaPago + '\'' +
                ", creadoEn=" + creado + '\'' +
                ", editadoEn=" + editado;
    }
}
