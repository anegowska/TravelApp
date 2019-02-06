package com.anegowska.services;

import com.anegowska.dao.CustomerDao;
import com.anegowska.dao.PurchaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class PurchaseDeleteService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerDeleteService.class);

    @Inject
    private PurchaseDao purchaseDao;

    public void delete(HttpServletRequest req) {

        String pidParam = req.getParameter("pid");
        Long pid = Long.valueOf(pidParam);
        LOG.info("Deleting purchase with id = {}", pid);

        purchaseDao.delete(pid);
    }
}
