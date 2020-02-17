package ua.mamchur.servletproject.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/request", "/managerlist", "/masterlist", "/userlist"})
public class AuthFilter implements Filter {
    /**
     *We don't need to init anything. That's why method "init()" is empty
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            servletRequest.getServletContext().getRequestDispatcher("/login").forward(request, response);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}