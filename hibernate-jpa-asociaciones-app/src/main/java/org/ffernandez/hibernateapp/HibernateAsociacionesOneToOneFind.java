package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.ClienteDetalle;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 6L);


            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            em.persist(detalle);


            cliente.setDetalle(detalle);

            em.getTransaction().commit();



        } catch (Exception e){

            em.getTransaction().rollback();
            e.printStackTrace();


        }finally {
            em.close();
        }



    }





}
