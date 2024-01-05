package org.ffernandez.hibernateapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

@Embeddable // define una clase que se va a incrustar en otra entidad
public class Auditoria {
    @Column(name="creado_en")
    private LocalDateTime creadoEn;

    @Column(name="editado_en")
    private LocalDateTime editadoEn;

    @PrePersist // se ejecuta antes de persistir
    public void prePersist() {
        System.out.println("Inicializar algo antes de persistir");
        this.creadoEn = LocalDateTime.now();
    }

    @PreUpdate // se ejecuta antes de actualizar
    public void preUpdate() {
        System.out.println("Inicializar algo antes de actualizar");
        this.editadoEn = LocalDateTime.now();
    }

    public LocalDateTime getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(LocalDateTime creadoEn) {
        this.creadoEn = creadoEn;
    }

    public LocalDateTime getEditadoEn() {
        return editadoEn;
    }

    public void setEditadoEn(LocalDateTime editadoEn) {
        this.editadoEn = editadoEn;
    }
}
