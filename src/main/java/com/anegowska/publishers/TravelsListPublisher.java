package com.anegowska.publishers;

import com.anegowska.dao.TravelDao;
import com.anegowska.model.Travel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Stateless
public class TravelsListPublisher {

    private static final Logger LOG = LoggerFactory.getLogger(TravelsListPublisher.class);

    @Inject
    private TravelDao travelDao;

    public void publishAllTravels(Map<String, Object> model) {

        List<Travel> travels = travelDao.findAll();
        LOG.info("Found {} travels", travels.size());

        model.put("travels", travels);
    }
}
