package ua.mamchur.servletproject.servlet;

import org.apache.log4j.Logger;
import ua.mamchur.servletproject.model.User;
import ua.mamchur.servletproject.service.UserService;
import ua.mamchur.servletproject.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/registration"}, name = "registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServlet.class);
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.create(username, password);
        if (user == null) {
            LOGGER.warn("Can't do registration. User with such username already exist");
            session.setAttribute("message", resourceBundle.getString("registration.danger.alert"));
            session.setAttribute("type", "danger fade show");
            response.sendRedirect("registration");
        } else {
            LOGGER.info("New user was created. Username: " + username);
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }
}
