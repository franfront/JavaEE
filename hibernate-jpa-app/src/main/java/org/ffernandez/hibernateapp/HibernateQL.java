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

            System.out.println(nombre);

            System.out.println("===== consulta por campos personalizados =====");

            Object[] campos = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id = :idCliente", Object[].class)
                    .setParameter("idCliente", 6L)
                    .getSingleResult();

            Long id = (Long) campos[0];
            String nombre2 = (String) campos[1];
            String apellido = (String) campos[2];

            System.out.println("id: " + id + ", nombre: " + nombre2 + ", apellido: " + apellido);

            System.out.println("===== consulta por campos personalizados lista =====");

            List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                    .getResultList();

           // for(Object[] registro : registros) {
                registros.forEach(reg->{
                Long id2 = (Long) reg[0];
                String nombre3 = (String) reg[1];
                String apellido2 = (String) reg[2];
                System.out.println("id: " + id2 + ", nombre: " + nombre3 + ", apellido: " + apellido2);
                });
           // }


            em.close();



        }
}