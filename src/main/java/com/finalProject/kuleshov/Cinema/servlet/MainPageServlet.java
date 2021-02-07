package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.TicketDao;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.Seance;
import com.finalProject.kuleshov.Cinema.model.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("")
public class MainPageServlet extends HttpServlet {
    TicketDao ticketDao = null;
    SeanceDao seanceDao = null;
    FilmDao filmDao = null;

    public MainPageServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        ticketDao = new TicketDao();
        seanceDao = new SeanceDao();
        filmDao = new FilmDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showSchedule(req, resp);
    }


    private void showSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("showSchedule");
        List<Seance> seanceList = seanceDao.showAllSeance();
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();
        req.setAttribute("listSeances", seanceList);
        req.setAttribute("listIdFilms", selectFilmIdName);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}

