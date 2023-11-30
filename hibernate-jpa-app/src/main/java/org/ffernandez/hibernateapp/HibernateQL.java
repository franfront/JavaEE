package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.dominio.ClienteDto;
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

            System.out.println("===== consulta por cliente y forma pago =====");

            List<Object[]> registros2 = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                    .getResultList();

            registros2.forEach(reg->{
                Cliente cliente2 = (Cliente) reg[0];
                String formaPago = (String) reg[1];
                System.out.println("cliente: " + cliente2 + ", forma pago: " + formaPago);
            });

            System.out.println("===== consulta que puebla y devuelve un objeto entity personalizado =====");
            clientes = em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                    .getResultList();

            clientes.forEach(System.out::println);

            System.out.println("===== consulta que puebla y devuelve un objeto otro personalizado =====");
            List<ClienteDto>  clientes2 = em.createQuery("select new org.ffernandez.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                    .getResultList();

            clientes2.forEach(System.out::println);

            System.out.println("===== consulta con nombres de clientes =====");
            List<String> nombres = em.createQuery("select c.nombre from Cliente c", String.class)
                    .getResultList();
            nombres.forEach(System.out::println);

            System.out.println("===== consulta con nombres unicos de clientes =====");
            nombres = em.createQuery("select distinct(c.nombre) from Cliente c", String.class)
                    .getResultList();

            nombres.forEach(System.out::println);

            System.out.println("===== consulta con formas de pago unicas =====");
            List<String> formasPago = em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
                    .getResultList();
            formasPago.forEach(System.out::println);

            System.out.println("===== consulta con numero de formas de pago unicas =====");
            Long cantFormasPago = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
                    .getSingleResult();
            System.out.println("cantidad de formas de pago: " + cantFormasPago);

            System.out.println("===== consulta con nombre y apellidos concatenados =====");
            //List<String> nombreApellidos = em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombreCompleto from Cliente c", String.class)
            List<String> nombreApellidos = em.createQuery("select c.nombre || ' ' || c.apellido as nombreCompleto from Cliente c", String.class)
                    .getResultList();
            nombreApellidos.forEach(System.out::println);

            System.out.println("===== consulta con nombre y apellidos concatenados en mayuscula =====");
            nombreApellidos = em.createQuery("select upper(concat(c.nombre, ' ', c.apellido)) as nombreCompleto from Cliente c", String.class)
                    .getResultList();
            nombreApellidos.forEach(System.out::println);

            System.out.println("===== consulta para buscar por nombre =====");
            String param = "ep";
            clientes = em.createQuery("select c from Cliente c where upper(c.nombre) like upper(:nombreCliente)", Cliente.class)
                    .setParameter("nombreCliente", "%" + param + "%")
                    .getResultList();
            clientes.forEach(System.out::println);

            System.out.println("===== consulta por rangos =====");
            clientes = em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class)
                    .getResultList();
            clientes.forEach(System.out::println);

            System.out.println("===== consulta con orden =====");
            clientes = em.createQuery("select c from Cliente c order by c.nombre asc, c.apellido asc", Cliente.class)
                    .getResultList();
            clientes.forEach(System.out::println);

            System.out.println("===== consulta con total de registros de la tabla =====");
            Long totalRegistros = em.createQuery("select count(c) from Cliente c", Long.class)
                    .getSingleResult();
            System.out.println("total registros: " + totalRegistros);

            System.out.println("===== consulta con valor minimo del id =====");
            Long minId = em.createQuery("select min(c.id) from Cliente c", Long.class)
                    .getSingleResult();
            System.out.println("minimo id: " + minId);

            System.out.println("===== consulta con valor maximo del id =====");
            Long maxId = em.createQuery("select max(c.id) from Cliente c", Long.class)
                    .getSingleResult();
            System.out.println("maximo id: " + maxId);

            System.out.println("===== consulta con nombre y su largo =====");
            registros = em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class)
                    .getResultList();
            registros.forEach(
                    reg->{
                        String nombre3 = (String) reg[0];
                        Integer largo = (Integer) reg[1];
                        System.out.println("nombre: " + nombre3 + ", largo: " + largo);
                    }
            );

            System.out.println("===== consulta con el nombre mas corto ====");
            Integer minLargoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class)
                    .getSingleResult();
            System.out.println("minimo largo nombre: " + minLargoNombre);

            System.out.println("===== consulta con el nombre mas largo ====");
            Integer maxLargoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class)
                    .getSingleResult();
            System.out.println("maximo largo nombre: " + maxLargoNombre);

            System.out.println("===== consultas resumen funciones agregaciones count min max avg sum ====");
            Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                    .getSingleResult();

            Long min = (Long) estadisticas[0];
            Long max = (Long) estadisticas[1];
            Long sum = (Long) estadisticas[2];
            Long count = (Long) estadisticas[3];
            Double avg = (Double) estadisticas[4];

            System.out.println("min: " + min + ", max: " + max + ", sum: " + sum + ", count: " + count + ", avg: " + avg);



            em.close();



        }
}
