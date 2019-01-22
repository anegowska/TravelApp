package com.anegowska.dao;

import com.anegowska.model.City;
import com.anegowska.model.Country;
import com.anegowska.model.Hotel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CountryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Country c) {
        entityManager.persist(c);
        return c.getId();
    }

    public Country update(Country c) {
        return entityManager.merge(c);
    }

    public void delete(Long id) {
        final Country c = entityManager.find(Country.class, id);
        if (c != null) {
            entityManager.remove(c);
        }
    }

    public Country findById(Long id) {
        return entityManager.find(Country.class, id);
    }

    public List<Country> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM Country c");

        return query.getResultList();
    }
}
