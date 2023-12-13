package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.ffernandez.hibernateapp.entity.Cliente;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class HibrenateCriteriaBusquedaDinamica {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Filtro para nombre: ");
        String nombre = sc.nextLine();

        System.out.println("Filtro para apellido: ");
        String apellido = sc.nextLine();

        System.out.println("Filtro para la forma de pago: ");
        String formaPago = sc.nextLine();


        EntityManager em = JpaUtil.getEntityManager();


        CriteriaBuilder cb = em.getCriteriaBuilder();


        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);


        Root<Cliente> from = query.from(Cliente.class);

        //
        List<Predicate> condiciones = new java.util.ArrayList<>();

        if (nombre != null && !nombre.isEmpty()) {
            condiciones.add(cb.equal(from.get("nombre"), nombre));
        }





    }
}
