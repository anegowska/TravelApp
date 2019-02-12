package com.anegowska.services;

import com.anegowska.dao.CityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class CityDeleteService {

    private static final Logger LOG = LoggerFactory.getLogger(CityDeleteService.class);

    @Inject
    private CityDao cityDao;

    public void delete(HttpServletRequest req) {

        String cidParam = req.getParameter("cid");
        Long cid = Long.valueOf(cidParam);
        LOG.info("Deleting city with id = {}", cid);

        cityDao.delete(cid);
    }
}
