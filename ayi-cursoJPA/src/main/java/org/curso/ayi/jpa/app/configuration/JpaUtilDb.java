package org.curso.ayi.jpa.app.configuration;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtilDb {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory(){
        return Persistence.createEntityManagerFactory("cursoAyiJPA");
    }

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
