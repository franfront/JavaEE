package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import javax.swing.*;

public class HibernateEditar {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try {

            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente a editar: "));
            Cliente c = em.find(Cliente.class, id);

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ", c.getNombre());
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido del cliente: ", c.getApellido());
            String formaPago = JOptionPane.showInputDialog("Ingrese la forma de pago del cliente: ", c.getFormaPago());
            em.getTransaction().begin();
            c.setNombre(nombre);
            c.setApellido(apellido);
            c.setFormaPago(formaPago);
            em.merge(c);


            em.getTransaction().commit();
            System.out.println(c);

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();

        }


    }
}
