package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.Factura;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToOneFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();


        try{
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, 1L);



            Factura factura = new Factura("Compras de oficina", 1000L);
            factura.setCliente(cliente);
            em.persist(factura);


            System.out.println("Factura: " + factura.getCliente());
            em.getTransaction().commit();

        } catch (Exception e){
            em.getTransaction().rollback();

        }finally {
            em.close();
        }


    }
}
