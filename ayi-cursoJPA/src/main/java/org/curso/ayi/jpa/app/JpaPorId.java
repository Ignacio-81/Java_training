package org.curso.ayi.jpa.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.curso.ayi.jpa.app.configuration.JpaUtilDb;
import org.curso.ayi.jpa.app.entity.Cliente;

import java.util.List;
import java.util.Scanner;

public class JpaPorId {
    public static void main(String[] args) {

        EntityManager em = JpaUtilDb.getEntityManager();
        Scanner s = new Scanner(System.in);

        System.out.println("Ingrese el id: ");

        Long id = s.nextLong();

        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);

        Cliente cliente2 = em.find(Cliente.class, id);
        System.out.println(cliente2);
        em.close();

    }
}
