package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.dao.RepairRequestStatusDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.RepairRequest;
import ua.mamchur.servletproject.model.RepairRequestStatus;
import ua.mamchur.servletproject.model.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/request", name = "request")
public class RepairRequestServlet extends HttpServlet {

    UserDao userDao = DaoFactory.getInstance().createUserDao();
    RepairRequestDao repairRequestDao = DaoFactory.getInstance().createRepairRequestDao();
    RepairRequestStatusDao repairRequestStatusDao = DaoFactory.getInstance().createRepairRequestStatusDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String theme = request.getParameter("theme");
        String description = request.getParameter("description");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        String currentStatus = "CURRENT_REQUEST";
        User user = userDao.findByUsername(username).get();
        RepairRequestStatus repairRequestStatus = repairRequestStatusDao.findByStatus(currentStatus);

        RepairRequest repairRequest = new RepairRequest();
        repairRequest.setTheme(theme);
        repairRequest.setDescription(description);
        repairRequest.setActive(true);
        repairRequest.setStatus(repairRequestStatus);
        repairRequest.setRequestCreator(user);
        repairRequestDao.save(repairRequest);

        response.sendRedirect("request");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("request.jsp").forward(request, response);

    }
}
