package com.anegowska.services;

import com.anegowska.dao.CustomerDao;
import com.anegowska.dao.PurchaseDao;
import com.anegowska.dao.TravelDao;
import com.anegowska.model.Customer;
import com.anegowska.model.Purchase;
import com.anegowska.model.Travel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class PurchaseSaveService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerSaveService.class);

    @Inject
    private PurchaseDao purchaseDao;

    @Inject
    private CustomerDao customerDao;

    @Inject
    private TravelDao travelDao;

    public void save(HttpServletRequest req) {

        Long cid = Long.valueOf(req.getParameter("cid"));
        Long tid = Long.valueOf(req.getParameter("tid"));

        Customer customer = customerDao.findById(cid);
        Travel travel = travelDao.findById(tid);

        Purchase purchase = new Purchase(customer, travel);
        purchaseDao.save(purchase);
        LOG.info("Saved a new purchase: {}", purchase);
    }
}
