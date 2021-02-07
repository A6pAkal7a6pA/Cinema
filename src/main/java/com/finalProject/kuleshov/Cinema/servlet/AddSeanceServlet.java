package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.Seance;
import com.finalProject.kuleshov.Cinema.model.User;

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
    FilmDao filmDao = null;
    SeanceDao seanceDao = null;

    @Override
    public void init() throws ServletException {
        filmDao = new FilmDao();
        seanceDao = new SeanceDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            showNewSeanceForm(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/account");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int filmSeance = Integer.parseInt(req.getParameter("filmSeanceId"));
        System.out.println(filmSeance);
        String dateSeance = req.getParameter("dateSeance");
        System.out.println(dateSeance);
        String timeSeance = req.getParameter("timeSeance");
        System.out.println(timeSeance);
        double priceSeance = Double.parseDouble(req.getParameter("priceSeance"));
        System.out.println(priceSeance);

        Seance seance = new Seance(dateSeance, filmSeance, timeSeance, priceSeance);

        seanceDao.addSeance(seance);

        resp.sendRedirect(req.getContextPath() + "/account");
    }

    public void showNewSeanceForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();
        req.setAttribute("selectFilmIdName", selectFilmIdName);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/admin/seance_form.jsp");
        dispatcher.forward(req, resp);
    }
}
