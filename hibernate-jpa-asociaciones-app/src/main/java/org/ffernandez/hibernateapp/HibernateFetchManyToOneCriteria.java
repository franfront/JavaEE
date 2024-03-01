package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.ffernandez.hibernateapp.entity.Factura;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToOneCriteria {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Factura> query = cb.createQuery(Factura.class);

        Root<Factura> factura = query.from(Factura.class); // indica la base de la consulta
        query.select(factura);

        List<Factura> facturas = em.createQuery(query).getResultList();

        facturas.forEach(f -> System.out.println(f.getDescripcion() + ", cliente: " + f.getCliente().getNombre()));


        em.close();




    }
}
