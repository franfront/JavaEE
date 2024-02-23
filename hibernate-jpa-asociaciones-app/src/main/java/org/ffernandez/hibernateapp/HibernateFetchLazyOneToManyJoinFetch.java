package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateFetchLazyOneToManyJoinFetch {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        Cliente cliente = em.createQuery("select c from Cliente c left outer join fetch c.direcciones left outer join fetch c.detalle where c.id=:id", Cliente.class)
                .setParameter("id", 1L)
                .getSingleResult();
        System.out.println("Cliente: "+ cliente.getNombre() +", direcciones: " + cliente.getDirecciones());
        System.out.println("Cliente: "+ cliente.getNombre() +", detalle: " + cliente.getDetalle());



        em.close();



    }
}
