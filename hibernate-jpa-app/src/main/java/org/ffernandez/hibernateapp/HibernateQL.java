package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateQL {
        public static void main(String[] args) {

            EntityManager em = JpaUtil.getEntityManager();
            System.out.println("===== consultar todos =====");
            List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class)
                    .getResultList();
            clientes.forEach(System.out::println);

            System.out.println("===== consultar por id =====");
            Cliente cliente = em.createQuery("select c from Cliente c where c.id = :idCliente", Cliente.class)
                    .setParameter("idCliente", 1L)
                    .getSingleResult();

            System.out.println(cliente);

            System.out.println("===== consulta solo en nombre por id =====");
            String nombre = em.createQuery("select c.nombre from Cliente c where c.id = :idCliente", String.class)
                    .setParameter("idCliente", 6L)
                    .getSingleResult();



        }
}
