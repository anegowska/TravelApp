package com.anegowska.dao;

import com.anegowska.model.City;
import com.anegowska.model.Hotel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CityDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(City c) {
        entityManager.persist(c);
        return c.getId();
    }

    public City update(City c) {
        return entityManager.merge(c);
    }

    public void delete(Long id) {
        final City c = entityManager.find(City.class, id);
        if (c != null) {
            entityManager.remove(c);
        }
    }

    public City findById(Long id) {
        return entityManager.find(City.class, id);
    }

    public List<City> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM City c");

        return query.getResultList();
    }
}
