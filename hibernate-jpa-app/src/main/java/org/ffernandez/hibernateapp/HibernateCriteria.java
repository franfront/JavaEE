package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder(); // Crea el constructor de criterios

        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class); // Crea el criterio de consulta

        Root<Cliente> from = query.from(Cliente.class); // Crea el objeto raiz de la consulta

        query.select(from); // Selecciona el objeto raiz de la consulta

        List<Cliente> clientes = em.createQuery(query).getResultList(); // Ejecuta la consulta

        clientes.forEach(System.out::println);

        System.out.println("===== listar where equals =====");

        // reinicamos la consulta
        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<String> nombreParam = cb.parameter(String.class, "nombre");

        query.select(from).where(cb.equal(from.get("nombre"), nombreParam));
        clientes = em.createQuery(query)
                .setParameter("nombre", "Pepe")
                .getResultList();

        clientes.forEach(System.out::println);

        System.out.println("===== usando where like para buscar por nombre =====");

        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<String> nombreParamLike = cb.parameter(String.class, "nombreParamLike");

        query.select(from).where(cb.like(cb.upper(from.get("nombre")), cb.upper(nombreParamLike)));
        clientes = em.createQuery(query)
                .setParameter("nombreParamLike", "%P%")
                .getResultList();

        clientes.forEach(System.out::println);

        System.out.println("===== usando where betqeen para rangos =====");

        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(cb.between(from.get("id"), 1, 7));
        clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);

        System.out.println("===== consulta where in =====");

        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).where(from.get("nombre").in("Pepe", "Naruto"));
        clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);


        System.out.println("===== filtrar usando predicados mayor que o igual que =====");

        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        //query.select(from).where(cb.ge(from.get("id"), 5)); mayor o igual que
        query.select(from).where(cb.gt(cb.length(from.get("nombre")), 5L)); // mayor que
        clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);

        System.out.println("==== consulta con los predicados conjuncion and y disyuncion or =====");

        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        Predicate porNombre = cb.equal(from.get("nombre"), "Pepe");
        Predicate porPago = cb.equal(from.get("formaPago"), "debito");
        Predicate porId = cb.ge(from.get("id"), 2);

       //query.select(from).where(cb.and(porNombre, porPago));

        //query.select(from).where(cb.or(porNombre, porPago));

        query.select(from).where(cb.and(porId, cb.or(porPago, porNombre)));

       clientes = em.createQuery(query).getResultList();

       clientes.forEach(System.out::println);


        System.out.println("===== consultas con irder by asc desc =====");

        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        query.select(from).orderBy(cb.asc(from.get("nombre")), cb.desc(from.get("apellido")));

        clientes = em.createQuery(query).getResultList();

        clientes.forEach(System.out::println);

        System.out.println("===== consulta por id =====");

        query = cb.createQuery(Cliente.class);
        from = query.from(Cliente.class);

        ParameterExpression<Long> idParam = cb.parameter(Long.class, "idParam");

        query.select(from).where(cb.equal(from.get("id"), idParam));

        Cliente cliente = em.createQuery(query)
                .setParameter("idParam", 1L)
                .getSingleResult();



        System.out.println(cliente);

        System.out.println("===== consulta solo el nombre de los clientes =====");

        // nueva query para solo obtener el nombre de los clientes
        CriteriaQuery<String> query2 = cb.createQuery(String.class);

        from = query2.from(Cliente.class);
        query2.select(from.get("nombre"));

        List<String> nombres = em.createQuery(query2).getResultList();

        nombres.forEach(System.out::println);

        System.out.println("===== consulta solo el nombre de los clientes unicos =====");


        query2 = cb.createQuery(String.class);

        from = query2.from(Cliente.class);
        query2.select(cb.upper(from.get("nombre"))).distinct(true);

        nombres = em.createQuery(query2).getResultList();



        nombres.forEach(System.out::println);

        System.out.println("===== consulta por nombres y apellidos concatenados =====");

        query2 = cb.createQuery(String.class);
        from = query2.from(Cliente.class);

        query2.select(cb.upper(cb.concat(cb.concat(from.get("nombre"), " "), from.get("apellido"))));

        nombres = em.createQuery(query2).getResultList();

        nombres.forEach(System.out::println);

        System.out.println("===== consulta de campos personalizados del entity cliente =====");

        CriteriaQuery<Object[]> queryObject = cb.createQuery(Object[].class);

        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));

        List<Object[]> lista = em.createQuery(queryObject).getResultList();

        lista.forEach(arr -> {
            Long id = (Long) arr[0];
            String nombre = (String) arr[1];
            String apellido = (String) arr[2];

            System.out.println( "Id: " +id + ", Nombre: " + nombre + ", Apellido: " + apellido);
        });

        System.out.println("===== consulta de campos personalizados del entity cliente con where =====");

        queryObject = cb.createQuery(Object[].class);

        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(cb.like(from.get("nombre"), "P%"));

        lista = em.createQuery(queryObject).getResultList();

        lista.forEach(arr -> {
            Long id = (Long) arr[0];
            String nombre = (String) arr[1];
            String apellido = (String) arr[2];

            System.out.println( "Id: " +id + ", Nombre: " + nombre + ", Apellido: " + apellido);
        });


        System.out.println("===== consulta de campos personalizados del entity cliente con where id =====");

        queryObject = cb.createQuery(Object[].class);

        from = queryObject.from(Cliente.class);
        queryObject.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(cb.equal(from.get("id"), 2L));
        Object[] registro = em.createQuery(queryObject).getSingleResult();


        Long id = (Long) registro[0];
        String nombre = (String) registro[1];
        String apellido = (String) registro[2];

        System.out.println( "Id: " +id + ", Nombre: " + nombre + ", Apellido: " + apellido);




        em.close();
    }
}
