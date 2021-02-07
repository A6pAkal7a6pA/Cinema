package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.Seance;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/account/seances_list")
public class AccountSeancesListServlet extends HttpServlet {
    SeanceDao seanceDao = null;
    FilmDao filmDao = null;

    @Override
    public void init() throws ServletException {
        seanceDao = new SeanceDao();
        filmDao = new FilmDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showSeancesList(req, resp);
    }

    private void showSeancesList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Seance> seanceList = seanceDao.showAllSeance();
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();

        req.setAttribute("listSeances", seanceList);
        req.setAttribute("listIdFilms", selectFilmIdName);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/seances_list.jsp");
        dispatcher.forward(req, resp);
    }
}
