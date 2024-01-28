package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.ClienteDetalle;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOne {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Homero", "Simpson");
            cliente.setFormaPago("debito");
            em.persist(cliente);


            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);


            cliente.setDetalle(detalle);

            em.getTransaction().commit();

            System.out.println("Cliente: " + cliente.getDetalle());

        } catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();


        }finally {
            em.close();
        }



    }





}
