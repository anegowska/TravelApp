package com.anegowska.web;

import com.anegowska.dao.CustomerDao;
import com.anegowska.dao.PurchaseDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.Customer;
import com.anegowska.model.Purchase;
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

@WebServlet("/search")
public class SearchTravelsByCustomer extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SearchTravelsByCustomer.class);

    private static final String TEMPLATE_NAME = "/search";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CustomerDao customerDao;

    @Inject
    private PurchaseDao purchaseDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();

        sendModelToTemplate(resp, model);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long cid = Long.valueOf(req.getParameter("cid"));
        Customer customer = customerDao.findById(cid);

        List<Purchase> purchases = purchaseDao.findByCustomer(cid);
        Map<String, Object> model = new HashMap<>();

        model.put("purchases", purchases);
        model.put("name", customer.getName());
        model.put("surname", customer.getSurname());

        sendModelToTemplate(resp, model);
    }

    private void sendModelToTemplate(HttpServletResponse resp, Map<String, Object> model) throws IOException {

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
