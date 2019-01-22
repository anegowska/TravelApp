package com.anegowska.dao;

import com.anegowska.model.City;
import com.anegowska.model.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class CustomerDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(Customer c) {
        entityManager.persist(c);
        return c.getId();
    }

    public Customer update(Customer c) {
        return entityManager.merge(c);
    }

    public void delete(Long id) {
        final Customer c = entityManager.find(Customer.class, id);
        if (c != null) {
            entityManager.remove(c);
        }
    }

    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    public List<Customer> findAll() {
        final Query query = entityManager.createQuery("SELECT c FROM Customer c");

        return query.getResultList();
    }
}
