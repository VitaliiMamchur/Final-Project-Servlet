package ua.mamchur.servletproject.servlet;

import org.apache.log4j.Logger;
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
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/masterlist"}, name = "masterlist")
public class MasterRepairRequestServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MasterRepairRequestServlet.class);
    private RepairRequestService repairRequestService = new RepairRequestServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        Long requestID = Long.parseLong(request.getParameter("id"));
        repairRequestService.closeRequestByMaster(requestID);
        LOGGER.info("The request with id " + requestID + " was closed successfully");
        session.setAttribute("message", resourceBundle.getString("masterlist.success.alert"));
        session.setAttribute("type", "success fade show");
        response.sendRedirect("masterlist");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RepairRequest> repairRequests = repairRequestService.showMasterList();
        request.setAttribute("repairRequests", repairRequests);
        request.getRequestDispatcher("masterlist.jsp").forward(request, response);
    }
}
