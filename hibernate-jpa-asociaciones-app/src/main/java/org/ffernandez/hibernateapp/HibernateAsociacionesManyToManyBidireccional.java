package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Alumno;
import org.ffernandez.hibernateapp.entity.Curso;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToManyBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Marge", "Simpson");
            Alumno alumno2 = new Alumno("Bart", "Simpson");
            Alumno alumno3 = new Alumno("Lisa", "Simpson");


            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate", "Andres");

            alumno1.addCurso(curso1);
            alumno1.addCurso(curso2);

            alumno2.addCurso(curso1);

            alumno3.addCurso(curso2);

            em.persist(alumno1);
            em.persist(alumno2);
            em.persist(alumno3);



            em.getTransaction().commit();

            System.out.println("Alumno: " + alumno1);
            System.out.println("Alumno: " + alumno2);
            System.out.println("Alumno: " + alumno3);

            em.getTransaction().begin();

            // Curso c2 = em.find(Curso.class, 3L);
            Curso c2 = new Curso("Curso Java", "Andres");
            c2.setId(3L);
            alumno1.removeCurso(c2);

            em.getTransaction().commit();
            System.out.println("Alumno: " + alumno1);


        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }




    }
}
