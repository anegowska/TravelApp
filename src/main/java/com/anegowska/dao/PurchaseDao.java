package com.anegowska.dao;

import com.anegowska.model.Purchase;
import com.anegowska.model.Travel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PurchaseDao {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Purchase p) {
        LOG.info("Saving purchase: {}", p);
        entityManager.persist(p);

        return p.getId();
    }

    public Purchase update(Purchase p) {
        LOG.info("Updating purchase: {}", p);
        return entityManager.merge(p);
    }

    public void delete(Long id) {
        LOG.info("Deleting purchase with id: {}", id);
        final Purchase p = entityManager.find(Purchase.class, id);
        if (p != null) {
            entityManager.remove(p);
        }else {
            LOG.warn("Could not find purchase with id: {}", id);
        }
    }

    public Purchase findById(Long id) {
        return entityManager.find(Purchase.class, id);
    }

    public List<Purchase> findAll() {
        final Query query = entityManager.createQuery("SELECT p FROM Purchase p");

        List<Purchase> result = query.getResultList();
        LOG.info("Found {} purchases.", result.size());
        return result;
    }

    public List<Purchase> findByCustomer(Long cid) {
        final Query query = entityManager.createQuery("SELECT p FROM Purchase p WHERE p.customer.id = :cid");

        query.setParameter("cid", cid);

        List<Purchase> result = query.getResultList();
        LOG.info("Found {} purchases.", result.size());
        return result;
    }

    public List<Purchase> findByTravel(Long tid) {
        final Query query = entityManager.createQuery("SELECT p FROM Purchase p WHERE p.travel.id = :tid");

        query.setParameter("tid", tid);

        List<Purchase> result = query.getResultList();
        LOG.info("Found {} purchases.", result.size());
        return result;
    }
}
