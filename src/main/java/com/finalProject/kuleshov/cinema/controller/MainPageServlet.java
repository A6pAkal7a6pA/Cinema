package com.finalProject.kuleshov.cinema.controller;

import com.finalProject.kuleshov.cinema.dao.FilmDao;
import com.finalProject.kuleshov.cinema.dao.SeanceDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.cinema.entity.Film;
import com.finalProject.kuleshov.cinema.entity.Seance;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(urlPatterns = {""})
public class MainPageServlet extends HttpServlet {
    private SeanceDao seanceDao = null;
    private FilmDao filmDao = null;

    @Override
    public void init() throws ServletException {
        seanceDao = new MySQLSeanceDao();
        filmDao = new MySQLFilmDao();
    }

    public MainPageServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showSchedule(req, resp);
    }

    private void showSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();

        req.setAttribute("listIdFilms", selectFilmIdName);
        String sort = req.getParameter("sortRequest");
        req.getSession().setAttribute("sortSession", sort);

        String page = req.getParameter("page");

        int pageId = 1;

        if (page != null) {
            pageId = Integer.parseInt(page);
        }
        req.setAttribute("page", pageId);
        int total = 4;


        if (pageId != 1) {
            pageId = ((pageId - 1) * total + 1);
        }

        int size = (int) Math.ceil((double) seanceDao.showAllSeance().size() / total);

        List<Integer> listPagination = new ArrayList<>();

        for (int i = 1; i <= size; i++) {
            listPagination.add(i);
        }

        req.setAttribute("listPagination", listPagination);
        req.setAttribute("maxPage", listPagination.size());

        List<Seance> seanceList = seanceDao.showAllSeance(sort, pageId, total);
        req.setAttribute("listSeances", seanceList);

        String currentDay = seanceDao.selectCurrentDay();
        req.setAttribute("currentDay", currentDay);

        List<Seance> seances = seanceDao.showAllSeance();
        req.setAttribute("covers", seances);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

}

