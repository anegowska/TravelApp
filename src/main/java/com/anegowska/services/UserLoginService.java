package com.anegowska.services;

import com.anegowska.dao.UserDao;
import com.anegowska.exceptions.UserNotFoundException;
import com.anegowska.model.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserLoginService {

    @Inject
    private UserDao userDao;

    public User findUser(String userEmail, String userPassword) throws UserNotFoundException {
        return userDao.findByEmailAndPassword(userEmail, userPassword);
    }
}
