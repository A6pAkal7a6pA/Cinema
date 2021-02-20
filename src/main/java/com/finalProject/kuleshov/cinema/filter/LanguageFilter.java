package com.finalProject.kuleshov.cinema.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class LanguageFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //filter init
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpSession session = req.getSession();
        String language = "en";
        if (session.getAttribute("language") != null) {
            language = (String) session.getAttribute("language");
        }
        session.setAttribute("language", language);

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //filter destroy
    }
}
