package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.dao.RepairRequestStatusDao;
import ua.mamchur.servletproject.dao.UserDao;
import ua.mamchur.servletproject.dao.implementation.JDBCDaoFactory;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet(urlPatterns = "/masterlist", name = "masterlist")
public class MasterRepairRequestServlet extends HttpServlet {
    DaoFactory daoFactory = new JDBCDaoFactory();
    RepairRequestDao repairRequestDao = daoFactory.createRepairRequestDao();
    RepairRequestStatusDao repairRequestStatusDao = daoFactory.createRepairRequestStatusDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentStatus = "CLOSED_REQUEST";
        Long requestID = Long.parseLong(request.getParameter("id"));
        repairRequestDao.updateById(requestID);
        response.sendRedirect("masterlist");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentStatus = "ACCEPTED_REQUEST";
        RepairRequestStatus repairRequestStatus = repairRequestStatusDao.findByStatus(currentStatus);
        List<RepairRequest> allRepairRequests = repairRequestDao.findAll();
        List<RepairRequest> repairRequests = new ArrayList<>();
        for (RepairRequest a : allRepairRequests) {
            if(a.getStatus().getStatus().equals(repairRequestStatus.getStatus())) {
                repairRequests.add(a);
            }
        }
        request.setAttribute("repairRequests", repairRequests);
        request.getServletContext().getRequestDispatcher("/masterlist.jsp").forward(request, response);
    }
}
