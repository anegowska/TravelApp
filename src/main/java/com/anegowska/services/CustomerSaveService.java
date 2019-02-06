package com.anegowska.services;

import com.anegowska.dao.CustomerDao;
import com.anegowska.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class CustomerSaveService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerSaveService.class);

    @Inject
    private CustomerDao customerDao;

    public void save(HttpServletRequest req) {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Long pesel = Long.valueOf(req.getParameter("pesel"));
        Long phone = Long.valueOf(req.getParameter("phone"));

        Customer customer = new Customer(name, surname, pesel, phone);
        customerDao.save(customer);
        LOG.info("Saved a new customer: {}", customer);
    }
}
