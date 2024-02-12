package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.find(Cliente.class, 1L);
        System.out.println("Cliente: " + cliente.getDirecciones());



        em.close();



    }
}
