package com.finalProject.kuleshov.cinema.controller.admin;

import com.finalProject.kuleshov.cinema.dao.FilmDao;
import com.finalProject.kuleshov.cinema.dao.SeanceDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.cinema.entity.Film;
import com.finalProject.kuleshov.cinema.entity.Seance;
import com.finalProject.kuleshov.cinema.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Double.*;
import static java.lang.Integer.parseInt;

@WebServlet("/seances_list")
public class AccountSeancesListServlet extends HttpServlet {
    private SeanceDao seanceDao = null;
    private FilmDao filmDao = null;

    @Override
    public void init() throws ServletException {
        seanceDao = new MySQLSeanceDao();
        filmDao = new MySQLFilmDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int filmSeance = parseInt(req.getParameter("filmSeanceId"));
        String dateSeance = req.getParameter("dateSeance");
        String timeSeance = req.getParameter("timeSeance");
        double priceSeance = parseDouble(req.getParameter("priceSeance"));

        Seance seance = new Seance(dateSeance, filmSeance, timeSeance, priceSeance);

        seanceDao.addSeance(seance);

        resp.sendRedirect(req.getContextPath() + "/seances_list");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Seance> seanceList = seanceDao.showAllSeance();
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();

        req.setAttribute("selectFilmIdName", selectFilmIdName);
        req.setAttribute("currentDate", Util.getCurrentDate());
        req.setAttribute("listSeances", seanceList);
        req.setAttribute("listIdFilms", selectFilmIdName);

        req.getRequestDispatcher("/WEB-INF/view/admin/seances_list.jsp").forward(req, resp);
    }

}
