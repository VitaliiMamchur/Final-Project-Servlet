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

@WebServlet(urlPatterns = {"/managerlist"}, name = "managerlist")
public class ManagerRepairRequestServlet extends HttpServlet {

    RepairRequestService repairRequestService = new RepairRequestServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("acceptId") != null) {
            Integer price = Integer.parseInt(request.getParameter("price"));
            Long requestId = Long.parseLong(request.getParameter("acceptId"));
            repairRequestService.acceptRequestByManager(requestId, price);
            session.setAttribute("message", "The request has been accepted successfully");
            session.setAttribute("type", "success fade show");
            response.sendRedirect("managerlist");
        } else if (request.getParameter("declineId") != null) {
            Long requestID = Long.parseLong(request.getParameter("declineId"));
            repairRequestService.declineRequestByManager(requestID);
            session.setAttribute("message", "The request has been declined successfully");
            session.setAttribute("type", "danger fade show");
            response.sendRedirect("managerlist");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RepairRequest> repairRequests = repairRequestService.showManagerList();
        ;
        request.setAttribute("repairRequests", repairRequests);
        request.getRequestDispatcher("managerlist.jsp").forward(request, response);
    }
}
