package com.anegowska.dao;

import com.anegowska.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class HotelDao {

    private static final Logger LOG = LoggerFactory.getLogger(HotelDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Hotel h) {
        LOG.info("Saving hotel: {}", h);
        entityManager.persist(h);
        return h.getId();
    }

    public Hotel update(Hotel h) {
        LOG.info("Updating hotel: {}", h);
        return entityManager.merge(h);
    }

    public void delete(Long id) {
        LOG.info("Deleting hotel with id: {}", id);
        final Hotel h = entityManager.find(Hotel.class, id);
        if (h != null) {
            entityManager.remove(h);
        }else {
            LOG.warn("Could not find hotel with id: {}", id);
        }
    }

    public Hotel findById(Long id) {
        return entityManager.find(Hotel.class, id);
    }

    public List<Hotel> findAll() {
        final Query query = entityManager.createQuery("SELECT h FROM Hotel h");

        List<Hotel> result = query.getResultList();
        LOG.info("Found {} hotels.", result.size());
        return result;
    }
}
