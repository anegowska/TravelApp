package com.anegowska.dao;

import com.anegowska.model.Hotel;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class HotelDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Hotel h) {
        entityManager.persist(h);
        return h.getId();
    }

    public Hotel update(Hotel h) {
        return entityManager.merge(h);
    }

    public void delete(Long id) {
        final Hotel h = entityManager.find(Hotel.class, id);
        if (h != null) {
            entityManager.remove(h);
        }
    }

    public Hotel findById(Long id) {
        return entityManager.find(Hotel.class, id);
    }

    public List<Hotel> findAll() {
        final Query query = entityManager.createQuery("SELECT h FROM Hotel h");

        return query.getResultList();
    }
}
