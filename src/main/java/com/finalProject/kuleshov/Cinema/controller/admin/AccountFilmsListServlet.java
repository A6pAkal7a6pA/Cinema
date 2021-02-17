package com.finalProject.kuleshov.Cinema.controller.admin;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.Cinema.entity.Film;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/films_list")
public class AccountFilmsListServlet extends HttpServlet {
    private FilmDao filmDao = null;

    @Override
    public void init() throws ServletException {
        filmDao = new MySQLFilmDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showFilmsList(req, resp);
    }

    private void showFilmsList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Film> allFilms = filmDao.findAllFilms();

        req.setAttribute("listFilms", allFilms);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/films_list.jsp");
        dispatcher.forward(req, resp);
    }
}
