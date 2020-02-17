package ua.mamchur.servletproject.servlet;

import org.apache.log4j.Logger;
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

@WebServlet(urlPatterns = {"/login"}, name = "login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = userService.login(username, password);
        if (role == null) {
            LOGGER.warn("There is no users with username: " + username);
            session.setAttribute("message", resourceBundle.getString("login.danger.alert"));
            session.setAttribute("type", "danger fade show");
            response.sendRedirect("login");
        } else {
            LOGGER.info("User " + username + " logged in successfully");
            session.setAttribute("user", username);
            session.setAttribute("role", role);
            response.sendRedirect("index");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
