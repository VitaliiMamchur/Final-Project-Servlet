package ua.mamchur.servletproject.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/managerlist"})
public class ManagerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        if (!(role.equals("ROLE_MANAGER"))) {
            servletRequest.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
        }

        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
