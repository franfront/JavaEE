package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.Factura;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToManyBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Augusto", "Argento");
            cliente.setFormaPago("debito");

            Factura f1 = new Factura("Compras de supermercado", 1000L);

            Factura f2 = new Factura("Compras de tecnologia", 10000L);

            cliente.addFactura(f1);
            cliente.addFactura(f2);


            em.persist(cliente);

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
