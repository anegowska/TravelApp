package com.anegowska.services;

import com.anegowska.dao.CountryDao;
import com.anegowska.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class CountrySaveService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerSaveService.class);

    @Inject
    private CountryDao countryDao;

    public void save(HttpServletRequest req) {

        String name = req.getParameter("name");

        Country country = new Country(name);
        countryDao.save(country);
        LOG.info("Saved a new country: {}", country);
    }
}
