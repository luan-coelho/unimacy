package br.unitins.unimacy.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory emf = null;

    private JPAUtil() {
    }

    public static EntityManager getEntityManager() {
        if (emf == null) emf = Persistence.createEntityManagerFactory("Unimacy");
        return emf.createEntityManager();
    }

}