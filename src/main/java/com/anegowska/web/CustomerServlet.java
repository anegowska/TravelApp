package com.anegowska.web;

import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.services.CustomerDeleteService;
import com.anegowska.services.CustomerSaveService;
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

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerServlet.class);

    private static final String TEMPLATE_NAME = "add-customer";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CustomerSaveService customerSaveService;

    @Inject
    private CustomerDeleteService customerDeleteService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.addHeader("Content-Type", "text/html; charset=utf-8");

        Map<String, Object> model = new HashMap<>();

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
            customerSaveService.save(req);
        } else if ("delete".equals(action)) {
            customerDeleteService.delete(req);
        }

        resp.sendRedirect("/customers");
    }
}
