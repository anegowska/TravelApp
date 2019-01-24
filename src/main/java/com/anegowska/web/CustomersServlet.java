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
import java.util.List;
import java.util.Map;

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CustomersServlet.class);

    private static final String TEMPLATE_NAME = "/customers";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CustomerDao customerDao;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();
        List<Customer> customers = customerDao.findAll();
        LOG.info("Found {} customers", customers.size());

        model.put("customers", customers);

        Template template = templateProvider.getTemplate(
                getServletContext(), TEMPLATE_NAME
        );

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            LOG.error("Error while processing template: ", e);
        }
    }
}
