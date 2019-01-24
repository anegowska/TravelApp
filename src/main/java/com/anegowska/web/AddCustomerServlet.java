package com.anegowska.web;

import com.anegowska.dao.CustomerDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.Customer;
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
public class AddCustomerServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(AddCustomerServlet.class);

    private static final String TEMPLATE_NAME = "add-customer";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CustomerDao customerDao;

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

        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        Long pesel = Long.valueOf(req.getParameter("pesel"));
        Long phone = Long.valueOf(req.getParameter("phone"));

        Customer customer = new Customer(name, surname, pesel, phone);
        customerDao.save(customer);

        resp.sendRedirect("/customers");
    }
}
