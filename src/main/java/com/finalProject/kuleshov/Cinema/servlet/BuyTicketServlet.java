package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.TicketDao;
import com.finalProject.kuleshov.Cinema.model.Seance;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Integer.parseInt;

@WebServlet("/buy_ticket")
public class BuyTicketServlet extends HttpServlet {
    SeanceDao seanceDao = null;
    TicketDao ticketDao = null;
    Map<Integer, String> mapPlaces = null;

    @Override
    public void init() throws ServletException {
        seanceDao = new SeanceDao();
        ticketDao = new TicketDao();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost");
        String[] hall_checks = req.getParameterValues("hall_check");

        System.out.println("getParametr seance");
        int idSeance = parseInt(req.getParameter("id"));
        System.out.println("getParam user");
        int idUser = (int) req.getSession().getAttribute("id");

        System.out.println(idUser);

        for (String place : hall_checks) {
            ticketDao.buyTicket(idUser, idSeance, Integer.parseInt(place));
        }

        resp.sendRedirect(req.getContextPath() + "/");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGetBuy");
        showBuyForm(req, resp);
    }

    private void showBuyForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = parseInt(request.getParameter("id"));
        Seance seance = seanceDao.selectSeanceById(id);
        List<Integer> list = ticketDao.selectOccupiedPlaces(id);
        int freePlacesBySeanceId = ticketDao.findFreePlacesBySeanceId(id);
        System.out.println(freePlacesBySeanceId);


        mapPlaces = new ConcurrentHashMap<>();

        for (int i = 1; i <= 18; i++) {
            mapPlaces.put(i, "");
            for (Integer integer : list) {
                if (i == integer) {
                    mapPlaces.put(i, "disabled");
                }
            }
        }



        request.setAttribute("mapPlaces", mapPlaces);
        request.setAttribute("seance", seance);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/buy_form.jsp");
        dispatcher.forward(request, response);
    }

}