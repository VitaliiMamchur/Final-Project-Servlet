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

@WebServlet(urlPatterns = {"/userlist"}, name = "userlist")
public class UserRepairRequestServlet extends HttpServlet {

    RepairRequestService repairRequestService = new RepairRequestServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Long requestID = Long.parseLong(request.getParameter("id"));
        String feedback = request.getParameter("feedback");
        if (repairRequestService.addFeedbackByUser(requestID, feedback)) {
            session.setAttribute("message", "The feedback has been added successfully");
            session.setAttribute("type", "success fade show");
        } else {
            session.setAttribute("message", "The feedback can't be added till the request will be closed");
            session.setAttribute("type", "danger fade show");
            response.sendRedirect("userlist");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        List<RepairRequest> repairRequests = repairRequestService.showUserList(username);
        request.setAttribute("repairRequests", repairRequests);
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }
}
