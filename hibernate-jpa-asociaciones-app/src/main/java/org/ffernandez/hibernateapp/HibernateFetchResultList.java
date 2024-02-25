package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchResultList {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        // trae todos los clientes y sus direcciones a la vez, en vez de hacer una consulta por cada cliente
        List<Cliente> clientes = em.createQuery("select distinct c from Cliente c left outer join fetch c.direcciones left outer join fetch c.detalle ", Cliente.class)
                .getResultList();

        clientes.forEach(c -> System.out.println(c.getNombre() + ", direcciones: " + c.getDirecciones()) );



        em.close();



    }
}
