package com.anegowska.services;

import com.anegowska.dao.CityDao;
import com.anegowska.dao.CountryDao;
import com.anegowska.dao.HotelDao;
import com.anegowska.model.City;
import com.anegowska.model.Country;
import com.anegowska.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class HotelSaveService {

    private static final Logger LOG = LoggerFactory.getLogger(CitySaveService.class);

    @Inject
    private HotelDao hotelDao;

    @Inject
    private CityDao cityDao;

    @Inject
    private CountryDao countryDao;

    public void save(HttpServletRequest req) {

        String name = req.getParameter("name");
        Integer stars = Integer.valueOf(req.getParameter("stars"));
        Long cityId = Long.valueOf(req.getParameter("cityId"));
        Long countryId = Long.valueOf(req.getParameter("countryId"));

        City city = cityDao.findById(cityId);
        Country country = countryDao.findById(countryId);

        Hotel hotel = new Hotel(name, stars, city, country);
        hotelDao.save(hotel);
        LOG.info("Saved a new hotel: {}", hotel);
    }
}
