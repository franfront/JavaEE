package org.ffernandez.hibernateapp;

import jakarta.persistence.EntityManager;
import org.ffernandez.hibernateapp.entity.Alumno;
import org.ffernandez.hibernateapp.entity.Curso;
import org.ffernandez.hibernateapp.util.JpaUtil;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        try{
            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Marge", "Simpson");
            Alumno alumno2 = new Alumno("Bart", "Simpson");
            Alumno alumno3 = new Alumno("Lisa", "Simpson");


            Curso curso1 = new Curso("Curso Java", "Andres");
            Curso curso2 = new Curso("Curso Hibernate", "Andres");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);

            alumno2.getCursos().add(curso1);

            alumno3.getCursos().add(curso2);

            em.persist(alumno1);
            em.persist(alumno2);
            em.persist(alumno3);



            em.getTransaction().commit();

            System.out.println("Alumno: " + alumno1);
            System.out.println("Alumno: " + alumno2);
            System.out.println("Alumno: " + alumno3);


        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }




    }
}
