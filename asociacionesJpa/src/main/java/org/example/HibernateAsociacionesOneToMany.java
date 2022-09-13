package org.example;

import jakarta.persistence.EntityManager;
import org.example.Entity.Cliente;
import org.example.Entity.Direccion;
import org.example.configurations.JpaUtilDb;

import java.util.List;

public class HibernateAsociacionesOneToMany {

    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();

        try{
            em.getTransaction().begin();

            Cliente cliente = new Cliente("Ignacio","Badella");
            cliente.setFormaPago("EFECTIVO");

            Direccion direccion1 = new Direccion("Independencia",150);
            Direccion direccion2 = new Direccion("9 de Julio",2566);

            /*cliente.getDirecciones().add(direccion1);
            cliente.getDirecciones().add(direccion2);*/


            cliente.setDirecciones(List.of(direccion1,direccion2));

            em.persist(cliente);

            em.getTransaction().commit();

            System.out.println(cliente);

            cliente = em.find(Cliente.class, cliente.getId());

            System.out.println("Cliente desde DB : "+cliente);

        }catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        

    }

}
