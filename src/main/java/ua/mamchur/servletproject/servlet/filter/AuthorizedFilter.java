package ua.mamchur.servletproject.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/registration", "/login"})
public class AuthorizedFilter implements Filter {

    /**
     *We don't need to init anything. That's why method "init()" is empty
     */
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null) {
            servletRequest.getServletContext().getRequestDispatcher("/index").forward(request, response);
        }

        chain.doFilter(request, response);
    }
    @Override
    public void destroy() {
    }
}
