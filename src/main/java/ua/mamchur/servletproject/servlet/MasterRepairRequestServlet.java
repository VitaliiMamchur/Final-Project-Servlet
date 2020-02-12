package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.dao.DaoFactory;
import ua.mamchur.servletproject.dao.RepairRequestDao;
import ua.mamchur.servletproject.model.RepairRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/masterlist"}, name = "masterlist")
public class MasterRepairRequestServlet extends HttpServlet {

    RepairRequestDao repairRequestDao = DaoFactory.getInstance().createRepairRequestDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long statusId = 2L;
        Long requestID = Long.parseLong(request.getParameter("id"));
        repairRequestDao.updateById(requestID, statusId);
        response.sendRedirect("masterlist");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long statusId = 1L;
        List<RepairRequest> repairRequests = repairRequestDao.findAllByStatusId(statusId);
        request.setAttribute("repairRequests", repairRequests);
        request.getRequestDispatcher("masterlist.jsp").forward(request, response);
    }
}
