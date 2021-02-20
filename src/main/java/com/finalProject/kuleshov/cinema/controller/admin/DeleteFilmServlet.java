package com.finalProject.kuleshov.cinema.controller.admin;

import com.finalProject.kuleshov.cinema.dao.FilmDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.cinema.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/delete_film")
public class DeleteFilmServlet extends HttpServlet {
    private FilmDao filmDao = null;

    @Override
    public void init() throws ServletException {
        filmDao = new MySQLFilmDao();
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
        filmDao.deleteFilmById(id);
        response.sendRedirect(request.getContextPath() + "/films_list");
    }
}
