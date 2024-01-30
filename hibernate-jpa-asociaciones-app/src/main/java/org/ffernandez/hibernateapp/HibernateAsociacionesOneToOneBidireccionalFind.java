package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.ClienteDetalle;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneBidireccionalFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 6L);

            ClienteDetalle detalle = new ClienteDetalle(true, 8000L);

            cliente.addDetalle(detalle);

            em.getTransaction().commit();
            System.out.println("Cliente: " + cliente);

        } catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();

        }finally {
            em.close();
        }



    }





}
