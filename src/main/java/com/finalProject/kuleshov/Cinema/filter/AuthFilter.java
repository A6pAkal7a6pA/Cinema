package com.finalProject.kuleshov.Cinema.filter;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/login")
public class AuthFilter implements Filter {
    User user = null;
    UserDao userDao = null;
    FilmDao filmDao = null;
    private String contextPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init /login");
        filmDao = new FilmDao();
        userDao = new UserDao();
        contextPath = filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("start doFilter Auth");
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();

        final String login = request.getParameter("login");
        final String password = request.getParameter("password");

        user = new User();
        user.setLogin(login);
        user.setPassword(password);


        if (session != null && session.getAttribute("login") != null && session.getAttribute("password") != null) {
            final User.ROLE role = (User.ROLE) session.getAttribute("role");

            moveToMenu(request, response, role);
        } else if (userDao.validate(user)) {
            final User.ROLE role = userDao.getRoleByLoginPassword(login, password);
            User user = userDao.selectUserByLogin(login);
            request.getSession().setAttribute("id", user.getId());
            request.getSession().setAttribute("password", password);
            request.getSession().setAttribute("login", login);
            request.getSession().setAttribute("role", role);

            moveToMenu(request, response, role);
        } else {
            moveToMenu(request, response, User.ROLE.UNKNOWN);
        }

        System.out.println("end doFilter Auth");
    }

    private void moveToMenu(final HttpServletRequest req, final HttpServletResponse res, final User.ROLE role)
            throws ServletException, IOException {
        System.out.println(role);
        if (role.equals(User.ROLE.ADMIN)) {
//            req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp").forward(req, res);
            res.sendRedirect(req.getContextPath() + "/account");
        } else if (role.equals(User.ROLE.USER)) {
//            req.getRequestDispatcher("/WEB-INF/view/user_menu.jsp").forward(req, res);
            res.sendRedirect(req.getContextPath() + "/account");
        } else {
            req.getRequestDispatcher("/WEB-INF/view/user_login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {

    }
}
