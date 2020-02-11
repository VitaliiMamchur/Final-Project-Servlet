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

@WebServlet(urlPatterns = "/managerlist", name = "managerlist")
public class ManagerRepairRequestServlet extends HttpServlet {

    RepairRequestDao repairRequestDao = DaoFactory.getInstance().createRepairRequestDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (request.getParameter("acceptId") != null) {
          Integer price = Integer.parseInt(request.getParameter("price"));
          String currentStatus = "ACCEPTED_REQUEST";
          Long requestID = Long.parseLong(request.getParameter("acceptId"));
          repairRequestDao.updateByManagerAccept(requestID, currentStatus, price);
          response.sendRedirect("managerlist");
      }
      else if (request.getParameter("declineId") != null) {
          String currentStatus = "DECLINED_REQUEST";
          Long requestID = Long.parseLong(request.getParameter("declineId"));
          repairRequestDao.updateById(requestID, currentStatus);
          response.sendRedirect("managerlist");
      }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long statusId = 0L;
        List<RepairRequest> repairRequests = repairRequestDao.findAllByStatusId(statusId);
        request.setAttribute("repairRequests", repairRequests);
        request.getServletContext().getRequestDispatcher("managerlist.jsp").forward(request, response);
    }
}
