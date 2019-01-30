package com.anegowska.dao;

import com.anegowska.model.Travel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TravelDao {

    private static final Logger LOG = LoggerFactory.getLogger(TravelDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Travel t) {
        LOG.info("Saving travel: {}", t);
        entityManager.persist(t);

        return t.getId();
    }

    public Travel update(Travel t) {
        LOG.info("Updating travel: {}", t);
        return entityManager.merge(t);
    }

    public void delete(Long id) {
        LOG.info("Deleting travel with id: {}", id);
        final Travel t = entityManager.find(Travel.class, id);
        if (t != null) {
            entityManager.remove(t);
        }else {
            LOG.warn("Could not find travel with id: {}", id);
        }
    }

    public Travel findById(Long id) {
        return entityManager.find(Travel.class, id);
    }

    public List<Travel> findAll() {
        final Query query = entityManager.createQuery("SELECT t FROM Travel t");

        List<Travel> result = query.getResultList();
        LOG.info("Found {} travles.", result.size());
        return result;
    }

    public List<Travel> findPurchasedByCustomer(Long cid) {
        final Query query = entityManager.createQuery("SELECT t FROM Travel t JOIN t.customers c " +
                "WHERE c.id =:cid");

        query.setParameter("cid", cid);

        List result = query.getResultList();
        LOG.info("Found {} travles.", result.size());
        return result;
    }
}
