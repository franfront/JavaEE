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
    // fetch = FetchType.LAZY -> carga perezosa, carga las direcciones solo cuando se necesiten
    // fetch = FetchType.EAGER -> carga ansiosa, carga las direcciones siempre
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //@JoinColumn(name = "id_cliente")
    @JoinTable(name= "tbl_clientes_direcciones", // nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_cliente"), // fk nombre de la columna que hace referencia a la tabla actual
            inverseJoinColumns = @JoinColumn(name = "id_direccion"), // nombre de la columna que hace referencia a la tabla de la otra entidad
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_direccion"})} // cada campo unico
    )
    private List<Direccion> direcciones;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura> facturas;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private ClienteDetalle detalle;



    public Cliente() {
        facturas = new ArrayList<>();
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


    public ClienteDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ClienteDetalle detalle) {
        this.detalle = detalle;
    }

    public void addDetalle(ClienteDetalle detalle) {

        this.detalle = detalle;
        detalle.setCliente(this);
    }

    public void removeDetalle() {

        detalle.setCliente(null);
        this.detalle = null;
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

    public Auditoria getAudit() {
        return audit;
    }

    public void setAudit(Auditoria audit) {
        this.audit = audit;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public void addFactura(Factura factura) {
        this.facturas.add(factura);
        factura.setCliente(this);
    }
    public void removeFactura(Factura factura) {
        this.facturas.remove(factura);
        factura.setCliente(null);

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
                ", facturas=" + facturas +
                ", detalle=" + detalle +
                '}';
    }

}
