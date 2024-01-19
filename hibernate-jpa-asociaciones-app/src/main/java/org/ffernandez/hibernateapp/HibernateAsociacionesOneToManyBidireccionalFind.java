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

            em.getTransaction().begin();

          //  Factura f5 = em.find(Factura.class, 1L);
            Factura f5  = new Factura("Compras de supermercado", 1000L);
            f5.setId(1L);
            cliente.removeFactura(f5);
            System.out.println("Cliente: " + cliente);


            em.getTransaction().commit();




        } catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }


    }
}
