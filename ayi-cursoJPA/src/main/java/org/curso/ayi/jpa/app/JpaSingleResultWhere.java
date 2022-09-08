package org.curso.ayi.jpa.app;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.curso.ayi.jpa.app.configuration.JpaUtilDb;
import org.curso.ayi.jpa.app.entity.Cliente;

import java.util.List;
import java.util.Scanner;

public class JpaSingleResultWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        EntityManager em = JpaUtilDb.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingrese una forma de pago: ");

        String pago = s.next();

        query.setParameter(1, pago);
/*        query.setMaxResults(1);
        Cliente c = (Cliente) query.getSingleResult();*/

        List<Cliente> c = (List<Cliente> ) query.getResultList();

        System.out.println(c);
        em.close();
    }
}
