package com.anegowska.services;

import com.anegowska.dao.HotelDao;
import com.anegowska.dao.TravelDao;
import com.anegowska.model.Hotel;
import com.anegowska.model.Travel;
import com.anegowska.model.enums.Board;
import com.anegowska.model.enums.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@Stateless
public class TravelSaveService {

    private static final Logger LOG = LoggerFactory.getLogger(TravelSaveService.class);

    @Inject
    private TravelDao travelDao;

    @Inject
    private HotelDao hotelDao;

    public void save(HttpServletRequest req) {

        Integer numberOfDays = Integer.valueOf(req.getParameter("numberOfDays"));
        Integer price = Integer.valueOf(req.getParameter("price"));
        Transport transport = Transport.valueOf(req.getParameter("transport"));
        Board board = Board.valueOf(req.getParameter("board"));
        Long hotelId = Long.valueOf(req.getParameter("hotelId"));

        Hotel hotel = hotelDao.findById(hotelId);

        Travel travel = new Travel(numberOfDays, price, transport, board, hotel);
        travelDao.save(travel);
        LOG.info("Saved a new travel: {}", travel);
    }
}
