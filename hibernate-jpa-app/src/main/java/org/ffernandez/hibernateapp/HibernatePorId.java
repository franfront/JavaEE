package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.id=?1", Cliente.class);
        System.out.println("Ingrese el id: ");
        Long pago = sc.nextLong();


        /* Otra forma de hacerlo
         Cliente cliente = em.find(Cliente.class, pago);
        */


        query.setParameter(1, pago);
        // castea el resultado a un objeto de tipo Cliente
        Cliente cliente = (Cliente) query.getSingleResult();





        System.out.println(cliente);

        em.close();

    }
}
