package com.anegowska.web;

import com.anegowska.dao.CityDao;
import com.anegowska.dao.CountryDao;
import com.anegowska.dao.HotelDao;
import com.anegowska.dao.TravelDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.*;
import com.anegowska.model.enums.Board;
import com.anegowska.model.enums.Transport;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loading")
public class LoadingServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoadingServlet.class);

    private static final String TEMPLATE_NAME = "loading";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CountryDao countryDao;

    @Inject
    private CityDao cityDao;

    @Inject
    private HotelDao hotelDao;

    @Inject
    private TravelDao travelDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        Map<String, Object> model = new HashMap<>();

        saveCountries();
        saveCities();
        saveHotels();
        saveTravels();

        Template template = templateProvider.getTemplate(
                getServletContext(), TEMPLATE_NAME
        );

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            LOG.error("Error while processing template: ", e);
        }
    }

    private void saveCountries() {

        countryDao.save(new Country("Spain"));
        countryDao.save(new Country("Italy"));
        countryDao.save(new Country("Portugal"));
    }

    private void saveCities() {

        cityDao.save(new City("Barcelona"));
        cityDao.save(new City("Venice"));
        cityDao.save(new City("Lisbon"));
    }

    private void saveHotels() {

        hotelDao.save(new Hotel("Hotel Dalia Ramblas", 3, cityDao.findById(1l), countryDao.findById(1l)));
        hotelDao.save(new Hotel("Hotel Palazzo Paruta", 4, cityDao.findById(2l), countryDao.findById(2l)));
        hotelDao.save(new Hotel("Hotel da Baixa", 4, cityDao.findById(3l), countryDao.findById(3l)));
    }

    private void saveTravels() {

        travelDao.save(new Travel(8, 3500, Transport.PLANE, Board.HB, hotelDao.findById(1l)));
        travelDao.save(new Travel(14, 5000, Transport.PLANE, Board.ALL_INCLUSIVE, hotelDao.findById(2l)));
        travelDao.save(new Travel(7, 4200, Transport.PLANE, Board.BREAKFAST, hotelDao.findById(3l)));
    }
}
