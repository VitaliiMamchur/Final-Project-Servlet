package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.model.RepairRequest;
import ua.mamchur.servletproject.service.RepairRequestService;
import ua.mamchur.servletproject.service.impl.RepairRequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/masterlist"}, name = "masterlist")
public class MasterRepairRequestServlet extends HttpServlet {

    RepairRequestService repairRequestService = new RepairRequestServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long requestID = Long.parseLong(request.getParameter("id"));
        repairRequestService.closeRequestByMaster(requestID);
        session.setAttribute("message", "The request has been closed successfully");
        session.setAttribute("type", "success fade show");
        response.sendRedirect("masterlist");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RepairRequest> repairRequests = repairRequestService.showMasterList();
        request.setAttribute("repairRequests", repairRequests);
        request.getRequestDispatcher("masterlist.jsp").forward(request, response);
    }
}
