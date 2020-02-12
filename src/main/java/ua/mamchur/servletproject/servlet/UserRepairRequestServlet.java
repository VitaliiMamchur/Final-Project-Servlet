package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.model.RepairRequest;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/userlist"}, name = "userlist")
public class UserRepairRequestServlet extends HttpServlet {

    UserDao userDao = DaoFactory.getInstance().createUserDao();
    RepairRequestDao repairRequestDao = DaoFactory.getInstance().createRepairRequestDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long requestID = Long.parseLong(request.getParameter("id"));
        String requestStatus = repairRequestDao.findById(requestID).get().getStatus().getStatus();
        if (requestStatus.equals("CLOSED_REQUEST")) {
            String feedback = request.getParameter("feedback");
            repairRequestDao.addFeedback(requestID, feedback);
            request.getServletContext().getRequestDispatcher("/userlist.jsp").include(request, response);
        }
        response.sendRedirect("userlist");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        Long userId = userDao.findByUsername(username).get().getId();
        List<RepairRequest> repairRequests = repairRequestDao.findAllByUserId(userId);
        request.setAttribute("repairRequests", repairRequests);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }
}
