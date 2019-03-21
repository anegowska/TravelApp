package com.anegowska.servlets;

import com.anegowska.dao.UserDao;
import com.anegowska.exceptions.UserNotFoundException;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.User;
import com.anegowska.services.UserLoginService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LoginServlet.class);

    private static final String TEMPLATE_NAME = "login";
    private static final String SESSION_EMAIL = "userEmail";

    @Inject
    private UserLoginService userLoginService;

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();

        sendModelToTemplate(resp, model);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Object> model = new HashMap<>();
        HttpSession session = req.getSession(true);

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        session.setAttribute(SESSION_EMAIL, email);

        try {
            userLoginService.findUser(email, password);
            LOG.info("User logged in");
            resp.sendRedirect("/loading");
        } catch (UserNotFoundException e) {
            List<String> errors = new ArrayList<>();
            errors.add("Bad email or password. Please try again.");
            model.put("errors", errors);
        }
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
