package com.anegowska.web;

import com.anegowska.dao.HotelDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.Hotel;
import com.anegowska.publishers.CitiesListPublisher;
import com.anegowska.publishers.CountriesListPublisher;
import com.anegowska.services.HotelDeleteService;
import com.anegowska.services.HotelSaveService;
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
import java.util.List;
import java.util.Map;

@WebServlet("/hotel")
public class HotelServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(HotelServlet.class);

    private static final String TEMPLATE_NAME = "/hotel";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private HotelDao hotelDao;

    @Inject
    private HotelSaveService hotelSaveService;

    @Inject
    private HotelDeleteService hotelDeleteService;

    @Inject
    private CitiesListPublisher citiesListPublisher;

    @Inject
    private CountriesListPublisher countriesListPublisher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();

        List<Hotel> hotels = hotelDao.findAll();
        LOG.info("Found {} hotels", hotels.size());

        model.put("hotels", hotels);

        citiesListPublisher.publishAllCities(model);
        countriesListPublisher.publishAllCountries(model);

        Template template = templateProvider.getTemplate(
                getServletContext(), TEMPLATE_NAME
        );

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            LOG.error("Error while processing template: ", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            hotelSaveService.save(req);
        } else if ("delete".equals(action)) {
            hotelDeleteService.delete(req);
        }

        doGet(req, resp);
    }
}
