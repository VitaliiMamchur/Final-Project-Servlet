package ua.mamchur.servletproject.servlet;

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

    UserService userService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userService.create(username, password);
        if (user == null) {
            session.setAttribute("message", resourceBundle.getString("registration.danger.alert"));
            session.setAttribute("type", "danger fade show");
            response.sendRedirect("registration");
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }
}
