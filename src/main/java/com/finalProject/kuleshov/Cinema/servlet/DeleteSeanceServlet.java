package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/account/delete_seance")
public class DeleteSeanceServlet extends HttpServlet {
    FilmDao filmDao = null;
    UserDao userDao= null;
    SeanceDao seanceDao = null;

    @Override
    public void init() throws ServletException {
        filmDao = new FilmDao();
        userDao = new UserDao();
        seanceDao = new SeanceDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) request.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            deleteFilm(request, response);
        }
    }
    private void deleteFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        seanceDao.deleteSeanceById(id);
        response.sendRedirect(request.getContextPath() + "/account");
    }
}
