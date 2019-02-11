package com.anegowska.services;

import com.anegowska.dao.CountryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class CountryDeleteService {

    private static final Logger LOG = LoggerFactory.getLogger(CountryDeleteService.class);

    @Inject
    private CountryDao countryDao;

    public void delete(HttpServletRequest req) {

        String cidParam = req.getParameter("cid");
        Long cid = Long.valueOf(cidParam);
        LOG.info("Deleting country with id = {}", cid);

        countryDao.delete(cid);
    }
}
