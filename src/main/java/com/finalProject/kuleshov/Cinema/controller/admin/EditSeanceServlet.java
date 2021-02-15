package com.finalProject.kuleshov.Cinema.controller.admin;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.Cinema.dto.Film;
import com.finalProject.kuleshov.Cinema.dto.Seance;
import com.finalProject.kuleshov.Cinema.dto.User;
import com.finalProject.kuleshov.Cinema.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet("/edit_seance")
public class EditSeanceServlet extends HttpServlet {
    SeanceDao seanceDao = null;
    FilmDao filmDao = null;

    @Override
    public void init() throws ServletException {
        seanceDao = new MySQLSeanceDao();
        filmDao = new MySQLFilmDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            showEditForm(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            updateSeance(req, resp);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        Seance seance = seanceDao.selectSeanceById(id);
        List<Film> selectFilmIdName = filmDao.selectFilmIdName();
        request.setAttribute("seance", seance);
        request.setAttribute("selectFilmIdName", selectFilmIdName);
        request.setAttribute("currentDate", Util.getCurrentDate());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/seance_form.jsp");
        dispatcher.forward(request, response);
    }

    private void updateSeance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        int filmId = parseInt(request.getParameter("filmSeanceId"));
        String date = request.getParameter("dateSeance");
        String time = request.getParameter("timeSeance");
        double price = Double.parseDouble(request.getParameter("priceSeance"));

        Seance updateSeance = new Seance(id, filmId, date, time, price);
        seanceDao.updateSeance(updateSeance);

        response.sendRedirect(request.getContextPath() + "/seances_list");
    }
}
