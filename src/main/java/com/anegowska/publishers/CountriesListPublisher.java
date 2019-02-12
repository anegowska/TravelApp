package com.anegowska.publishers;

import com.anegowska.dao.CountryDao;
import com.anegowska.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class CountriesListPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(CountriesListPublisher.class);

    @Inject
    private CountryDao countryDao;

    public void publishAllCountries(Map<String, Object> model){

        List<Country> countries = countryDao.findAll();
        LOG.info("Found {} countries", countries.size());

        model.put("countries", countries);
    }

}
