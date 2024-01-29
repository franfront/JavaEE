package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.ClienteDetalle;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Homero", "Thompson");
            cliente.setFormaPago("debito");

            ClienteDetalle detalle = new ClienteDetalle(true, 10000L);

            cliente.setDetalle(detalle);
            detalle.setCliente(cliente);

            em.persist(cliente);

        } catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            em.close();
        }



    }





}
