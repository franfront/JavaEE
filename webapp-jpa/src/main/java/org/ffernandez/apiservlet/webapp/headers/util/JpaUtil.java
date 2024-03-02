package org.ffernandez.apiservlet.webapp.headers.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory emf = buildEntityManagerFactory();


    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("ejemploJpa");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
}
