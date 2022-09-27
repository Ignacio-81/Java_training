package org.example;

import jakarta.persistence.EntityManager;
import org.example.Entity.Cliente;
import org.example.Entity.ClienteDetalle;
import org.example.Entity.Direccion;
import org.example.Entity.Factura;
import org.example.configurations.JpaUtilDb;

import java.util.ArrayList;
import java.util.List;

public class HibernateOnetoOne {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();

        try {
            em.getTransaction().begin();


            Cliente cliente1 = new Cliente("Ignacio", "Badella");
            cliente1.setFormaPago("EFECTIVO");
            em.persist(cliente1);

            Direccion direccion1 = new Direccion("Independencia",150);
            Direccion direccion2 = new Direccion("9 de Julio",2566);
            cliente1.setDirecciones(List.of(direccion1,direccion2));
            em.persist(direccion1);
            em.persist(direccion2);

            List<Factura> listFactura = new ArrayList<>();
            Factura factura1 = new Factura("Compra Super", 1000);
            listFactura.add(factura1);
            Factura factura2 = new Factura("Compra PC", 20000);
            listFactura.add(factura2);
            cliente1.setFacturas(listFactura);
            factura1.setCliente(cliente1);
            factura2.setCliente(cliente1);
            em.persist(factura1);
            em.persist(factura2);

            ClienteDetalle detalle = new ClienteDetalle(true, 5000L);
            detalle.setCliente(cliente1);
            cliente1.setDetalle(detalle);
            em.persist(detalle);

            //em.persist(detalle);

            em.getTransaction().commit();

            em.getTransaction().begin();

            Cliente clienteQuery = em.find(Cliente.class, 13L);
            System.out.println("ClienteQuery: " + clienteQuery);


            em.getTransaction().commit();
            // System.out.println(cliente);

        } catch (Exception ex) {
            em.getTransaction().rollback();

        } finally {
            em.close();
        }


    }
}
