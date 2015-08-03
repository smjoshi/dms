package com.dc.dms.base;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by SJoshi on 6/24/2015.
 */
public abstract class AbstractDmsDao {

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PersistenceContext
    protected EntityManager entityManager;

}
