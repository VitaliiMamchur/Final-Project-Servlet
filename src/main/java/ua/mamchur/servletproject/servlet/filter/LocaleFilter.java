package ua.mamchur.servletproject.servlet.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebFilter(filterName = "LocaleFilter", urlPatterns = {"/*"})
public class LocaleFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws ServletException, IOException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        if (request.getParameter("lang") != null) {
            request.getSession().setAttribute("lang", request.getParameter("lang"));
        }
        if (session.getAttribute("lang") != null) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("messages",
                    new Locale((String) session.getAttribute("lang")));
            session.setAttribute("resourceBundle", resourceBundle);
        } else {
            ResourceBundle defaultResourceBundle = ResourceBundle.getBundle("messages");
            session.setAttribute("resourceBundle", defaultResourceBundle);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
