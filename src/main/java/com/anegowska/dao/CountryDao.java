package com.anegowska.dao;

import com.anegowska.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CountryDao {

    private static final Logger LOG = LoggerFactory.getLogger(CountryDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Country c) {
        LOG.info("Saving country: {}", c);
        entityManager.persist(c);
        return c.getId();
    }

    public Country update(Country c) {
        LOG.info("Updating country: {}", c);
        return entityManager.merge(c);
    }

    public void delete(Long id) {
        LOG.info("Deleting country with id: {}", id);
        final Country c = entityManager.find(Country.class, id);
        if (c != null) {
            entityManager.remove(c);
        }else {
            LOG.warn("Could not find country with id: {}", id);
        }
    }

    public Country findById(Long id) {
        return entityManager.find(Country.class, id);
    }

    public List<Country> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM Country c");

        List<Country> result = query.getResultList();
        LOG.info("Found {} countries.", result.size());
        return result;
    }
}
