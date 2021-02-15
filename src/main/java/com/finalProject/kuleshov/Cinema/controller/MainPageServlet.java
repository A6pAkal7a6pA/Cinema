package com.finalProject.kuleshov.Cinema.controller;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.TicketDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLTicketDao;
import com.finalProject.kuleshov.Cinema.dto.Film;
import com.finalProject.kuleshov.Cinema.dto.Seance;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("")
public class MainPageServlet extends HttpServlet {
    TicketDao ticketDao = null;
    SeanceDao seanceDao = null;
    FilmDao filmDao = null;
    private static final Logger LOG = Logger.getLogger(MainPageServlet.class);

    @Override
    public void init() throws ServletException {
        ticketDao = new MySQLTicketDao();
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
        req.setAttribute("start", pageId);
        int total = 4;


        if (pageId == 1) {
        } else {
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

