package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Alumno;
import org.ffernandez.hibernateapp.entity.Curso;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToManyFindBidireccional {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Alumno alumno1 = em.find(Alumno.class, 1L);
            Alumno alumno2 = em.find(Alumno.class, 2L);
            Alumno alumno3 = em.find(Alumno.class, 3L);


            Curso curso1 = em.find(Curso.class, 1L); //new Curso("Curso Java", "Andres");
            Curso curso2 = em.find(Curso.class, 2L); //new Curso("Curso Hibernate", "Andres");

            alumno1.addCurso(curso1);
            alumno1.addCurso(curso2);

            alumno2.addCurso(curso1);

            alumno3.addCurso(curso2);




            em.getTransaction().commit();

            System.out.println("Alumno: " + alumno1);
            System.out.println("Alumno: " + alumno2);
            System.out.println("Alumno: " + alumno3);

            em.getTransaction().begin();


            Curso c2 = new Curso("Curso Java EE 9", "Andres");
            c2.setId(2L);
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
