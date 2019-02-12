package com.anegowska.web;

import com.anegowska.dao.CityDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.City;
import com.anegowska.services.CityDeleteService;
import com.anegowska.services.CitySaveService;
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

@WebServlet("/city")
public class CityServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CityServlet.class);

    private static final String TEMPLATE_NAME = "city";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CityDao cityDao;

    @Inject
    private CitySaveService citySaveService;

    @Inject
    private CityDeleteService cityDeleteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();

        List<City> cities = cityDao.findAll();
        LOG.info("Found {} cities", cities.size());

        model.put("cities", cities);

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
            citySaveService.save(req);
        } else if ("delete".equals(action)) {
            cityDeleteService.delete(req);
        }

        doGet(req, resp);
    }
}
