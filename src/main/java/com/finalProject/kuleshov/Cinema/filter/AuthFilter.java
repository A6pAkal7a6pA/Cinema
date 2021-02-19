package com.finalProject.kuleshov.Cinema.filter;

import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.Cinema.entity.User;
import com.finalProject.kuleshov.Cinema.util.Util;

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
    private String contextPath;
    private String message = "";
    String hashPassword = "";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init /login");
        filmDao = new MySQLFilmDao();
        userDao = new MySQLUserDao();
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("start doFilter Auth");
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
//            moveToMenu(request, response, role);
            response.sendRedirect(request.getContextPath() + "/");
        } else if (userDao.checkUser(user)) {
            final User.ROLE role = userDao.getRoleByLoginPassword(login, hashPassword);
            User user = userDao.selectUserByLogin(login);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("firstName", user.getFirstName());
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", role);

            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.getRequestDispatcher("/WEB-INF/view/user_login.jsp").forward(request, response);
        }

        System.out.println("end doFilter Auth");
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse res, final User.ROLE role)
            throws ServletException, IOException {
        System.out.println(role);
        if (role.equals(User.ROLE.ADMIN)) {
//            req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);

        } else if (role.equals(User.ROLE.USER)) {
//            req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);
            res.sendRedirect(req.getContextPath() + "/");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/user_login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
