package org.curso.ayi.jpa.app;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.configuration.JpaUtilDb;
import org.curso.ayi.jpa.app.entity.Cliente;

import javax.swing.*;
import java.time.LocalDate;


public class JpaCrear {

    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();
        try{
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
            String apellido = JOptionPane.showInputDialog("Ingrese el apellido:");
            String pago = JOptionPane.showInputDialog("Ingrese la forma de pago:");
            //LocalDate fechaCreacion = JOptionPane.showInputDialog("Ingrese la Fecha de Creacion");

            em.getTransaction().begin();

            Cliente clienteInsertar = new Cliente();
            clienteInsertar.setNombre(nombre    );
            clienteInsertar.setApellido(apellido);
            clienteInsertar.setFormaPago(pago);
            clienteInsertar.setFechaCreacion(LocalDate.now());

            em.persist(clienteInsertar);

            System.out.println("Se guardo el cliente en la BD ");
                // Si no damos commit el registro no queda guardado en la BD, se borra si no lo hacemos.
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
