package com.anegowska.dao;

import com.anegowska.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CustomerDao {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Customer c) {
        LOG.info("Saving customer: {}", c);
        entityManager.persist(c);
        return c.getId();
    }

    public Customer update(Customer c) {
        LOG.info("Updating customer: {}", c);
        return entityManager.merge(c);
    }

    public void delete(Long id) {
        LOG.info("Deleting customer with id: {}", id);
        final Customer c = entityManager.find(Customer.class, id);
        if (c != null) {
            entityManager.remove(c);
        }else {
            LOG.warn("Could not find customer with id: {}", id);
        }
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public Customer findByPesel(Long pesel) {
        return entityManager.find(Customer.class, pesel);
    }

    public List<Customer> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM Customer c");

        List<Customer> result = query.getResultList();
        LOG.info("Found {} customers.", result.size());
        return result;
    }
}
