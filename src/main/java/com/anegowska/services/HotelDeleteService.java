package com.anegowska.services;

import com.anegowska.dao.HotelDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class HotelDeleteService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerDeleteService.class);

    @Inject
    private HotelDao hotelDao;

    public void delete(HttpServletRequest req) {

        String hidParam = req.getParameter("hid");
        Long hid = Long.valueOf(hidParam);
        LOG.info("Deleting hotel with id = {}", hid);

        hotelDao.delete(hid);
    }
}
