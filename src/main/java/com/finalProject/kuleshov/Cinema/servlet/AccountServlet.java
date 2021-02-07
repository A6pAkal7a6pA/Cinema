package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.Seance;
import com.finalProject.kuleshov.Cinema.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
    FilmDao filmDao = null;
    UserDao userDao = null;
    SeanceDao scheduleDao = null;

    @Override
    public void init() throws ServletException {
        filmDao = new FilmDao();
        userDao = new UserDao();
        scheduleDao = new SeanceDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet");
        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            showProfileAdmin(req, resp);
        } else if (role.equals(User.ROLE.USER)) {
            showProfileUser(req, resp);
        } else {
            System.out.println(role);
            req.getRequestDispatcher("/WEB-INF/view/user_login.jsp").forward(req, resp);
        }
    }

    private void showProfileAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("start listFilm");
        String login = (String) request.getSession().getAttribute("login");
        User user = userDao.selectUserByLogin(login);
//        List<User> allUsers = userDao.findAllUsers();
//        List<Film> list = filmDao.findAllFilms();
        List<Seance> seances = scheduleDao.showAllSeance();
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();
        request.setAttribute("userName", user.getFirstName());
//        request.setAttribute("listUsers", allUsers);
//        request.setAttribute("listFilms", list);
        request.setAttribute("listSeances", seances);
        request.setAttribute("listIdFilms", selectFilmIdName);
//        response.sendRedirect(request.getContextPath() + "/account");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/admin_menu.jsp");
        dispatcher.forward(request, response);
        System.out.println("end listFilm");
    }

    private void showProfileUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("showProfileUser start");
        String login = (String) request.getSession().getAttribute("login");
        User user = userDao.selectUserByLogin(login);
        List<User> users = new ArrayList<>();
        users.add(user);
        System.out.println(users);
        request.setAttribute("userName", user.getFirstName());
        request.setAttribute("userProfile", users);
        System.out.println("showProfileUser end");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/user_menu.jsp");
        dispatcher.forward(request, response);

    }

}
