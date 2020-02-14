package ua.mamchur.servletproject.servlet;

import ua.mamchur.servletproject.service.RepairRequestService;
import ua.mamchur.servletproject.service.impl.RepairRequestServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ResourceBundle;

@WebServlet(urlPatterns = {"/request"}, name = "request")
public class RepairRequestServlet extends HttpServlet {

    RepairRequestService repairRequestService = new RepairRequestServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ResourceBundle resourceBundle = (ResourceBundle) session.getAttribute("resourceBundle");
        String theme = request.getParameter("theme");
        String description = request.getParameter("description");
        String username = (String) session.getAttribute("user");
        repairRequestService.create(theme, description, username);
        session.setAttribute("message", resourceBundle.getString("request.success.alert"));
        session.setAttribute("type", "success fade show");
        response.sendRedirect("request");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("request.jsp").forward(request, response);

    }
}
