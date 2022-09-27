package org.example;

import jakarta.persistence.EntityManager;
import org.example.Entity.Alumno;
import org.example.Entity.Curso;
import org.example.configurations.JpaUtilDb;

public class HibernateAsociacionesManyToMany {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();

        try{

            em.getTransaction().begin();

            Alumno alumno1 = new Alumno("Ignacio", "Badella");
            Alumno alumno2 = new Alumno("Gabriela", "Ludueña");

            Curso curso1 = new Curso("Curso Java", "Julian");
            Curso curso2 = new Curso("Curso Hibernate y Jpa", "Julian");

            alumno1.getCursos().add(curso1);
            alumno1.getCursos().add(curso2);
            alumno2.getCursos().add(curso1);

            em.persist(alumno1);
            em.persist(alumno2);


            em.getTransaction().commit();

           /* System.out.println(alumno1);
            System.out.println(alumno2);*/

            em.getTransaction().begin();

            Alumno alumnoQuery1 = em.find(Alumno.class, alumno1.getId());
            Alumno alumnoQuery2 = em.find(Alumno.class, 4L);

            System.out.println(alumnoQuery1);
            System.out.println(alumnoQuery2);

            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }
}
