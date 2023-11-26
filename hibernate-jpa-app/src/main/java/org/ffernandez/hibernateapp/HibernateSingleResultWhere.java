package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.Scanner;

public class HibernateSingleResultWhere {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago =?1", Cliente.class);
        System.out.println("Ingrese la forma de pago: ");
        String pago = sc.next();
        query.setParameter(1, pago);
        query.setMaxResults(1); // limita la cantidad de resultados a 1
        // castea el resultado a un objeto de tipo Cliente
        Cliente cliente = (Cliente) query.getSingleResult();
        System.out.println(cliente);

        em.close();

    }
}
