package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

//        EntityManager em = JpaUtil.getEntityManager();
//        Query query = em.createQuery("select c from Cliente c where c.id=?1", Cliente.class);
//        System.out.println("Ingrese el id: ");
//        Long id = sc.nextLong();

//        query.setParameter(1, id);
//        // castea el resultado a un objeto de tipo Cliente
//        Cliente cliente = (Cliente) query.getSingleResult();

        System.out.println("Ingrese el id: ");
        Long id = sc.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);

        Cliente cliente2 = em.getReference(Cliente.class, 2L);
        System.out.println(cliente2);




        em.close();

    }
}
