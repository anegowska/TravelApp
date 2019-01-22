package com.anegowska.dao;

import com.anegowska.model.Hotel;
import com.anegowska.model.Travel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TravelDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Travel t) {
        entityManager.persist(t);
        return t.getId();
    }

    public Travel update(Travel t) {
        return entityManager.merge(t);
    }

    public void delete(Long id) {
        final Travel t = entityManager.find(Travel.class, id);
        if (t != null) {
            entityManager.remove(t);
        }
    }

    public Travel findById(Long id) {
        return entityManager.find(Travel.class, id);
    }

    public List<Travel> findAll() {
        final Query query = entityManager.createQuery("SELECT t FROM Travel t");

        return query.getResultList();
    }
}
