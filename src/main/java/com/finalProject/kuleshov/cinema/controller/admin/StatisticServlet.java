package com.finalProject.kuleshov.cinema.controller.admin;

import com.finalProject.kuleshov.cinema.dao.TicketDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLTicketDao;
import com.finalProject.kuleshov.cinema.entity.Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/statistics")
public class StatisticServlet extends HttpServlet {
    TicketDao ticketDao = null;

    @Override
    public void init() throws ServletException {
        ticketDao = new MySQLTicketDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String periodFilms = req.getParameter("periodFilms");
        String periodAmount = req.getParameter("periodAmount");
        if (periodFilms == null) {
            periodFilms = "day";
        }
        if (periodAmount == null) {
            periodAmount = "day";
        }
        List<Ticket> allMovieByPopularity = ticketDao.findAllMovieByPopularity(periodFilms);
        List<Ticket> amountForPeriod = ticketDao.findAmountForPeriod(periodAmount);
        Ticket occupiedPlacesForYear = ticketDao.findOccupiedPlacesForYear();


        req.setAttribute("filmsByPopularity", allMovieByPopularity);
        req.setAttribute("amountForPeriod", amountForPeriod);
        req.setAttribute("occupiedPlaces", occupiedPlacesForYear);

        req.getRequestDispatcher("/WEB-INF/view/admin/statistics.jsp").forward(req, resp);
    }
}
