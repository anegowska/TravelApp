package com.anegowska.web;

import com.anegowska.dao.CityDao;
import com.anegowska.dao.CountryDao;
import com.anegowska.dao.HotelDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.City;
import com.anegowska.model.Country;
import com.anegowska.model.Hotel;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        Map<String, Object> model = new HashMap<>();

        saveCountries();
        saveCities();

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

}
