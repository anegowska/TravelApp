package com.anegowska.publishers;

import com.anegowska.dao.CustomerDao;
import com.anegowska.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class CustomersListPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(CustomersListPublisher.class);

    @Inject
    private CustomerDao customerDao;

    public void publishAlCustomers(Map<String, Object> model) {

        List<Customer> customers = customerDao.findAll();
        LOG.info("Found {} customers", customers.size());

        model.put("customers", customers);
    }
}
