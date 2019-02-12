package com.anegowska.services;

import com.anegowska.dao.CityDao;
import com.anegowska.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class CitySaveService {

    private static final Logger LOG = LoggerFactory.getLogger(CitySaveService.class);

    @Inject
    private CityDao cityDao;

    public void save(HttpServletRequest req) {

        String name = req.getParameter("name");

        City city = new City(name);
        cityDao.save(city);
        LOG.info("Saved a new city: {}", city);
    }
}
