package com.finalProject.kuleshov.cinema.filter;

import com.finalProject.kuleshov.cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.cinema.entity.User;
import com.finalProject.kuleshov.cinema.util.Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class AuthFilter implements Filter {
    User user = null;
    MySQLUserDao userDao = null;
    MySQLFilmDao filmDao = null;
    String hashPassword = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filmDao = new MySQLFilmDao();
        userDao = new MySQLUserDao();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        if (login != null && password != null) {
            hashPassword = Util.hash(password, "MD5");
        }
        user = new User();
        user.setLogin(login);
        user.setPassword(hashPassword);

        if (session != null && session.getAttribute("login") != null && session.getAttribute("password") != null) {
            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            moveToMenu(request, response, role);
        } else if (userDao.checkUser(user)) {
            final User.ROLE role = userDao.getRoleByLogin(login);
            user = userDao.selectUserByLogin(login);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("firstName", user.getFirstName());
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", role);

            moveToMenu(request, response, role);
        } else {
            moveToMenu(request, response, User.ROLE.UNKNOWN);
        }

    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse res, final User.ROLE role)
            throws ServletException, IOException {
        if (role.equals(User.ROLE.ADMIN)) {
            res.sendRedirect(req.getContextPath() + "/");
        } else if (role.equals(User.ROLE.USER)) {
            res.sendRedirect(req.getContextPath() + "/");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/user_login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
        //filter destroy
    }
}
