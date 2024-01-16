package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.Factura;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccionalFind {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = em.find(Cliente.class, 1L);

            Factura f1 = new Factura("Compras de supermercado", 1000L);

            Factura f2 = new Factura("Compras de tecnologia", 10000L);

            cliente.addFactura(f1);
            cliente.addFactura(f2);


            em.merge(cliente);

            em.getTransaction().commit();

            System.out.println("Cliente: " + cliente);


        } catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }


    }
}
