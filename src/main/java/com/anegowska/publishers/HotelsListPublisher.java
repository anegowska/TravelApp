package com.anegowska.publishers;

import com.anegowska.dao.HotelDao;
import com.anegowska.model.Hotel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class HotelsListPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(HotelsListPublisher.class);

    @Inject
    private HotelDao hotelDao;

    public void publishAllHotels(Map<String, Object> model) {

        List<Hotel> hotels = hotelDao.findAll();
        LOG.info("Found {} hotels", hotels.size());

        model.put("hotels", hotels);
    }
}
