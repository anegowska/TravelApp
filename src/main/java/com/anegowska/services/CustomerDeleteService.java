package com.anegowska.services;

import com.anegowska.dao.CustomerDao;
import com.anegowska.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class CustomerDeleteService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerDeleteService.class);

    @Inject
    private CustomerDao customerDao;

    public void delete(HttpServletRequest req) {

        String cidParam = req.getParameter("cid");
        Long cid = Long.valueOf(cidParam);
        LOG.info("Deleting customer with id = {}", cid);

        customerDao.delete(cid);
    }
}
