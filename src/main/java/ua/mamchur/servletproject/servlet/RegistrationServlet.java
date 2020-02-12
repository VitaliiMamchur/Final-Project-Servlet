package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RoleDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.Role;
import ua.mamchur.servletproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/registration"}, name = "registration")
public class RegistrationServlet extends HttpServlet {

    UserDao userDao = DaoFactory.getInstance().createUserDao();
    RoleDao roleDao = DaoFactory.getInstance().createRoleDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        String currentRole = "ROLE_USER";
        Optional<User> userFromDB = userDao.findByUsername(username);
        if (userFromDB.isPresent()) {
            request.getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
        }

        Role role = roleDao.findByRole(currentRole);
        user.setUsername(username);
        user.setPassword(password);
        user.setActive(true);
        user.setRole(role);
        userDao.save(user);
        response.sendRedirect("login");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("registration.jsp").forward(request, response);
    }
}
