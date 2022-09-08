package org.curso.ayi.jpa.app;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.configuration.JpaUtilDb;
import org.curso.ayi.jpa.app.entity.Cliente;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class JpaEliminar {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();

        Scanner s = new Scanner(System.in);
        System.out.println("Ingrese el id del cliente a eliminar:");
        Long id = s.nextLong();



        try{


            Cliente clienteEliminar = em.find(Cliente.class, id);
            if (clienteEliminar == null){
                System.out.println("Id Objeto cliente nulo");
            }
            em.getTransaction().begin();
            em.remove(clienteEliminar);
            em.getTransaction().commit();
        }catch(Exception e){
            //Si tenemos un problema en la transaccion volvemos atras y queda como estaba en la BD.
            em.getTransaction().rollback();
            e.getMessage();
        }finally {
            em.close();
        }


    }
}
