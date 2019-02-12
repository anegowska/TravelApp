package com.anegowska.publishers;

import com.anegowska.dao.CityDao;
import com.anegowska.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class CitiesListPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(CitiesListPublisher.class);

    @Inject
    private CityDao cityDao;

    public void publishAllCities(Map<String, Object> model) {

        List<City> cities = cityDao.findAll();
        LOG.info("Found {} cities", cities.size());

        model.put("cities", cities);
    }
}
