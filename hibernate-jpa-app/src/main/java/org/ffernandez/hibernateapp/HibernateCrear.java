package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class HibernateCrear {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente: ");
            String formaPago = JOptionPane.showInputDialog("Ingrese la forma de pago del cliente: ");

            em.getTransaction().begin(); // inicia la transacción

            Cliente c = new Cliente();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(formaPago);

            em.persist(c); // guarda el objeto en la base de datos




            em.getTransaction().commit(); // confirma la transacción

            System.out.println("Cliente guardado: " + c.getId());
            c = em.find(Cliente.class, c.getId());
            System.out.println(c);

        } catch (Exception e) {
            em.getTransaction().rollback(); // deshace la transacción
            e.printStackTrace();
        } finally {
            em.close();
        }


    }
}
