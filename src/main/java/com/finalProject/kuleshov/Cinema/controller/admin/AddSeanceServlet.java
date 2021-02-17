package com.finalProject.kuleshov.Cinema.controller.admin;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.Cinema.entity.Film;
import com.finalProject.kuleshov.Cinema.entity.Seance;
import com.finalProject.kuleshov.Cinema.entity.User;
import com.finalProject.kuleshov.Cinema.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/add_new_seance")
public class AddSeanceServlet extends HttpServlet {
    private FilmDao filmDao = null;
    private SeanceDao seanceDao = null;

    @Override
    public void init() throws ServletException {
        filmDao = new MySQLFilmDao();
        seanceDao = new MySQLSeanceDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            showNewSeanceForm(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/seances_list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int filmSeance = Integer.parseInt(req.getParameter("filmSeanceId"));
        String dateSeance = req.getParameter("dateSeance");
        String timeSeance = req.getParameter("timeSeance");
        double priceSeance = Double.parseDouble(req.getParameter("priceSeance"));

        Seance seance = new Seance(dateSeance, filmSeance, timeSeance, priceSeance);

        seanceDao.addSeance(seance);

        resp.sendRedirect(req.getContextPath() + "/seances_list");
    }

    public void showNewSeanceForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();
        req.setAttribute("selectFilmIdName", selectFilmIdName);
        req.setAttribute("currentDate", Util.getCurrentDate());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/seance_form.jsp");
        dispatcher.forward(req, resp);
    }

}
