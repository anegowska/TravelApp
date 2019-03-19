package com.anegowska.dao;

import com.anegowska.exceptions.UserNotFoundException;
import com.anegowska.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class UserDao {

    private static final Logger LOG = LoggerFactory.getLogger(UserDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    public Long save(User u) {
        LOG.info("Saving user: {}", u);
        entityManager.persist(u);
        return u.getUserId();
    }

    public User update(User u) {
        LOG.info("Updating user: {}", u);
        return entityManager.merge(u);
    }

    public void delete(Long id) {
        LOG.info("Deleting user with id: {}", id);
        final User u = entityManager.find(User.class, id);
        if (u != null) {
            entityManager.remove(u);
        }else {
            LOG.warn("Could not find user with id: {}", id);
        }
    }

    public User findByEmail(String userEmail) {
        return entityManager.find(User.class, userEmail);
    }

    public User findById(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        final Query query = entityManager.createQuery("SELECT u FROM User u");

        List<User> result = query.getResultList();
        LOG.info("Found {} users.", result.size());
        return result;
    }

    public User findByEmailAndPassword(String email, String password) throws UserNotFoundException {
        final Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.userEmail = :email " +
                "AND u.userPassword = :password");
        query.setParameter("email", email);
        query.setParameter("password", password);

        User user = (User) query.getSingleResult();
        if (user == null) {
            throw new UserNotFoundException();
        }
        return user;
    }
}
