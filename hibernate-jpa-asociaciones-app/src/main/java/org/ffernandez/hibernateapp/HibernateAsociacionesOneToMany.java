package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.Direccion;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Augusto", "Argento");

            cliente.setFormaPago("debito");

            Direccion d1 = new Direccion("Calle falsa", 123);
            Direccion d2 = new Direccion("Calle popo de perro", 888);

            cliente.getDirecciones().add(d1);
            cliente.getDirecciones().add(d2);

            em.persist(cliente);



            em.getTransaction().commit();

            System.out.println("Cliente: " + cliente);

            em.getTransaction().begin(); // nueva transaccion para eliminar

            cliente = em.find(Cliente.class, cliente.getId());

            cliente.getDirecciones().remove(d1);
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
