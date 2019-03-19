package com.anegowska.servlets;

import com.anegowska.dao.UserDao;
import com.anegowska.freemarker.TemplateProvider;
import com.anegowska.model.User;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterServlet.class);

    private static final String TEMPLATE_NAME = "register";
    private static final Integer ADMIN = 1;
    private static final Integer USER = 2;

    @Inject
    private UserDao userDao;

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
        User user = new User();

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        List<User> usersList = userDao.findAll();
        List<User> emailList = usersList.stream()
                .filter(e -> e.getUserEmail().equals(email))
                .collect(Collectors.toList());

        if (emailList.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            user.setUserEmail(email);
            user.setUserPassword(password);
            user.setUserName(name);
            user.setUserSurname(surname);

            if (isAdmin(email)) {
                user.setRole(ADMIN);
            }else{
                user.setRole(USER);
            }
            userDao.save(user);
            resp.sendRedirect("/login");

        }else {
            List<String> errors = new ArrayList<>();
            errors.add("This email already exists. Please try again.");
            model.put("errors", errors);
        }
        sendModelToTemplate(resp, model);
    }

    private boolean isAdmin(String email) {
        return email.equals("admin@gmail.com");
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
