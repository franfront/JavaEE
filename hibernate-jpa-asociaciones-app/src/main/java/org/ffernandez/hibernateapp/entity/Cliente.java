package org.ffernandez.hibernateapp.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    //cascade = CascadeType.ALL -> todas las operaciones que se hagan sobre el cliente se haran sobre las direcciones
    //orphanRemoval = true -> si se elimina un cliente se eliminan todas las direcciones asociadas
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_cliente")
    @JoinTable(name= "tbl_clientes_direcciones", // nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_cliente"), // fk nombre de la columna que hace referencia a la tabla actual
            inverseJoinColumns = @JoinColumn(name = "id_direccion"), // nombre de la columna que hace referencia a la tabla de la otra entidad
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_direccion"})} // cada campo unico
    )
    private List<Direccion> direcciones;

    public Cliente() {
        direcciones = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
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

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {

        LocalDateTime creado = this.audit != null ? this.audit.getCreadoEn() : null;
        LocalDateTime editado = this.audit != null ? this.audit.getEditadoEn() : null;



        return "{" + "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago=" + formaPago + '\'' +
                ", creadoEn=" + creado + '\'' +
                ", editadoEn=" + editado + '\'' +
                ", direcciones=" + direcciones + '\'' +
                '}';
    }
}
