package com.anegowska.web;

import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.publishers.CountriesListPublisher;
import com.anegowska.services.CountryDeleteService;
import com.anegowska.services.CountrySaveService;
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

@WebServlet("/country")
public class CountryServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CountryServlet.class);

    private static final String TEMPLATE_NAME = "country";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CountriesListPublisher countriesListPublisher;

    @Inject
    private CountrySaveService countrySaveService;

    @Inject
    private CountryDeleteService countryDeleteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();

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
            countrySaveService.save(req);
        } else if ("delete".equals(action)) {
            countryDeleteService.delete(req);
        }

        doGet(req, resp);
    }


}
