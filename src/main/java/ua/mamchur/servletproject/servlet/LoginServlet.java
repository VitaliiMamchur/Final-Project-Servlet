package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.dao.implementation.JDBCDaoFactory;
import ua.mamchur.servletproject.model.Role;
import ua.mamchur.servletproject.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login", name = "login")
public class LoginServlet extends HttpServlet {

    JDBCDaoFactory daoFactory = new JDBCDaoFactory();
    UserDao userDao = daoFactory.createUserDao();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if (userDao.checkUser(username, password)) {
            String role = userDao.findByUsername(username).get().getRole().getRole();
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            session.setAttribute("role", role);
            response.sendRedirect("index");

        } else {
            response.sendRedirect("registration");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
