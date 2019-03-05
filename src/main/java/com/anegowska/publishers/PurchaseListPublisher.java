package com.anegowska.publishers;

import com.anegowska.dao.PurchaseDao;
import com.anegowska.model.Purchase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class PurchaseListPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseListPublisher.class);

    @Inject
    private PurchaseDao purchaseDao;

    public void publishAllPurchases(Map<String, Object> model) {

        List<Purchase> purchases = purchaseDao.findAll();
        LOG.info("Found {} purchases", purchases.size());

        model.put("purchases", purchases);
    }
}
