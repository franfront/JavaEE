package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.entity.Factura;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Factura> query = cb.createQuery(Factura.class);

        Root<Factura> factura = query.from(Factura.class); // indica la base de la consulta
        Join<Factura, Cliente> cliente = (Join) factura.fetch("cliente", JoinType.LEFT);// hace un join con la tabla cliente
        cliente.fetch("detalle", JoinType.LEFT); // hace un join con la tabla cliente_detalle

        query.select(factura).where(cb.equal(cliente.get("id"), 1L)); // selecciona la factura donde el cliente sea el id 1

        List<Factura> facturas = em.createQuery(query).getResultList();

        facturas.forEach(f -> System.out.println(f.getDescripcion() + ", cliente: " + f.getCliente().getNombre()));


        em.close();




    }
}
