package org.example;

import jakarta.persistence.EntityManager;
import org.example.Entity.Cliente;
import org.example.Entity.Direccion;
import org.example.Entity.Factura;
import org.example.configurations.JpaUtilDb;

import java.util.ArrayList;
import java.util.List;

public class JpaOnetoManyBiDireccional {
    public static void main(String[] args) {
        EntityManager em = JpaUtilDb.getEntityManager();

        try{
            em.getTransaction().begin();
            List<Factura> listFactura = new ArrayList<>();

            Cliente cliente1 = new Cliente("Ignacio", "Martinez");
            cliente1.setFormaPago("EFECTIVO");

            Direccion direccion1 = new Direccion("Independencia",150);
            Direccion direccion2 = new Direccion("9 de Julio",2566);
            cliente1.setDirecciones(List.of(direccion1,direccion2));

            Factura factura1 = new Factura("Compra Super", 1000);
            listFactura.add(factura1);
            Factura factura2 = new Factura("Compra PC", 20000);
            listFactura.add(factura2);
            factura1.setCliente(cliente1);
            factura2.setCliente(cliente1);

            cliente1.setFacturas(listFactura);
            em.persist(cliente1);
            //em.getTransaction().commit();

            //em.getTransaction().begin();
            Cliente cliente2 = new Cliente("Juan", "Perez");
            cliente2.setFormaPago("TARJETA");
            Direccion direccion3 = new Direccion("cartago",45);
            Direccion direccion4 = new Direccion("Cambarta",125);
            cliente2.setDirecciones(List.of(direccion3,direccion4));

            List<Factura> listFactura2 = new ArrayList<>();
            Factura factura4 = new Factura("TV", 15000);
            factura4.setCliente(cliente2);
            listFactura.add(factura4);
            Factura factura3 = new Factura("Automovil", 354000);
            factura3.setCliente(cliente2);
            listFactura.add(factura3);
            cliente2.setFacturas(listFactura);

            em.persist(cliente2);

            em.getTransaction().commit();

            System.out.println("CLiente :" + cliente1);
            System.out.println("CLiente :" + cliente2);

            //Bidireccionalidad:
/*            em.getTransaction().begin();

            Cliente cliente3 = new Cliente("Martin", "Guzman");
            em.persist(cliente3);

            Factura factura3 = new Factura("Gastos Varios", 4500);

            factura3.setCliente(cliente3);
            em.persist(factura3);
            em.getTransaction().commit();

            System.out.println("Facturas :" + factura3);*/

        }catch(Exception e){
            em.getTransaction().rollback();
        }
        em.close();
    }
}
