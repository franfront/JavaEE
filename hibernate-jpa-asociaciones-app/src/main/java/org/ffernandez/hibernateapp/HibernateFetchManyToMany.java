package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Alumno;
import org.ffernandez.hibernateapp.util.JpaUtil;

import java.util.List;

public class HibernateFetchManyToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        // fetch indica que los datos se carguen de manera ansiosa
        List<Alumno> alumnos = em.createQuery("select distinct a from Alumno a left outer join fetch a.cursos", Alumno.class)
                .getResultList();
        alumnos.forEach(a -> System.out.println(a.getNombre() + ", cursos: " + a.getCursos()) );


        em.close();

    }
}
