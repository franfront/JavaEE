package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateListar {
    public static void main(String[] args) {

        //Obtener un EntityManager a través de JpaUtil
        EntityManager em = JpaUtil.getEntityManager();

        // La consulta se realiza utilizando JPQL (Java Persistence Query Language),
        List<Cliente> clientes = em.createQuery("select c from Cliente c").getResultList(); // busca todos de la clase Cliente en la tabla clientes

        clientes.forEach(System.out::println);

        // se cierra el EntityManager
        em.close();


    }
}
