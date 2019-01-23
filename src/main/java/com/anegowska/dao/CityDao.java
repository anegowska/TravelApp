package com.anegowska.dao;

import com.anegowska.model.City;
import com.anegowska.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CityDao {

    private static final Logger LOG = LoggerFactory.getLogger(CityDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(City c) {
        LOG.info("Saving city: {}", c);
        entityManager.persist(c);
        return c.getId();
    }

    public City update(City c) {
        LOG.info("Updating city: {}", c);
        return entityManager.merge(c);
    }

    public void delete(Long id) {
        LOG.info("Deleting city with id: {}", id);
        final City c = entityManager.find(City.class, id);
        if (c != null) {
            entityManager.remove(c);
        }else {
            LOG.warn("Could not find city with id: {}", id);
        }
    }

    public City findById(Long id) {
        return entityManager.find(City.class, id);
    }

    public List<City> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM City c");

        List<City> result = query.getResultList();
        LOG.info("Found {} cities.", result.size());
        return result;
    }
}
