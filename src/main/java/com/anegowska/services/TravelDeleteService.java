package com.anegowska.services;

import com.anegowska.dao.TravelDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class TravelDeleteService {

    private static final Logger LOG = LoggerFactory.getLogger(TravelDeleteService.class);

    @Inject
    private TravelDao travelDao;

    public void delete(HttpServletRequest req) {

        String tidParam = req.getParameter("tid");
        Long tid = Long.valueOf(tidParam);
        LOG.info("Deleting hotel with id = {}", tid);

        travelDao.delete(tid);
    }
}
