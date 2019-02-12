package com.anegowska.web;

import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.publishers.BoardTypePublisher;
import com.anegowska.publishers.HotelsListPublisher;
import com.anegowska.publishers.TransportTypePublisher;
import com.anegowska.publishers.TravelsListPublisher;
import com.anegowska.services.TravelDeleteService;
import com.anegowska.services.TravelSaveService;
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

@WebServlet("/travel")
public class TravelServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(TravelServlet.class);

    private static final String TEMPLATE_NAME = "/travel";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private TravelsListPublisher travelsListPublisher;

    @Inject
    private HotelsListPublisher hotelsListPublisher;

    @Inject
    private TravelSaveService travelSaveService;

    @Inject
    private TravelDeleteService travelDeleteService;

    @Inject
    private TransportTypePublisher transportTypePublisher;

    @Inject
    private BoardTypePublisher boardTypePublisher;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();

        travelsListPublisher.publishAllTravels(model);
        hotelsListPublisher.publishAllHotels(model);
        transportTypePublisher.publishAllTransportTypes(model);
        boardTypePublisher.publishAllBoardTypes(model);

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
            travelSaveService.save(req);
        } else if ("delete".equals(action)) {
            travelDeleteService.delete(req);
        }

        doGet(req, resp);
    }
}
