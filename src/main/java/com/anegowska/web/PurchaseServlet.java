package com.anegowska.web;

import com.anegowska.dao.CustomerDao;
import com.anegowska.dao.PurchaseDao;
import com.anegowska.dao.TravelDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.Customer;
import com.anegowska.model.Purchase;
import com.anegowska.model.Travel;
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
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(PurchaseServlet.class);

    private static final String TEMPLATE_NAME = "/purchase";

    @Inject
    private TemplateProvider templateProvider;

    @Inject
    private CustomerDao customerDao;

    @Inject
    private TravelDao travelDao;

    @Inject
    private PurchaseDao purchaseDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        Long cid = Long.valueOf(req.getParameter("cid"));
        Long travelId = Long.valueOf(req.getParameter("tid"));

        Customer customer = customerDao.findById(cid);
        Travel travel = travelDao.findById(travelId);

        Purchase purchase = new Purchase(customer, travel);
        purchaseDao.save(purchase);

        resp.sendRedirect("/search");
    }

}
