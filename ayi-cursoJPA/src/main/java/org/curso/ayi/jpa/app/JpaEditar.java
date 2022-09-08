package org.curso.ayi.jpa.app;

import jakarta.persistence.EntityManager;
import org.curso.ayi.jpa.app.configuration.JpaUtilDb;
import org.curso.ayi.jpa.app.entity.Cliente;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Scanner;

public class JpaEditar {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();


        try{

            Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente a modificar:"));
            Cliente c = em.find(Cliente.class, id);
            if (c == null){
                System.out.println("Id Objeto cliente nulo");
            }else {
                String nombre = JOptionPane.showInputDialog("Ingrese el nombre:", c.getNombre());
                String apellido = JOptionPane.showInputDialog("Ingrese el apellido:", c.getApellido());
                String pago = JOptionPane.showInputDialog("Ingrese la forma de pago:", c.getFormaPago());

                em.getTransaction().begin();

                c.setNombre(nombre);
                c.setApellido(apellido);
                c.setFormaPago(pago);
                c.setFechaCreacion(LocalDate.now());
                c.getId();
                System.out.println("ID del CLiente :" +c.getId());
                em.merge(c);
                System.out.println("Cliente actualizado:" +c.toString());

                em.getTransaction().commit();
            }
        }catch(Exception e){
            //Si tenemos un problema en la transaccion volvemos atras y queda como estaba en la BD.
            em.getTransaction().rollback();
            e.getMessage();
        }finally {
            em.close();
        }


    }
}
