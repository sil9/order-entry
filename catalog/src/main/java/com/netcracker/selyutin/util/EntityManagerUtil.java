package com.netcracker.selyutin.util;


import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private EntityManager entityManager;

    private EntityManagerUtil() {
        entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    private static class Holder {
        private final static EntityManagerUtil INSTANCE = new EntityManagerUtil();
    }

    public static EntityManagerUtil getInstance() {
        return Holder.INSTANCE;
    }
}
