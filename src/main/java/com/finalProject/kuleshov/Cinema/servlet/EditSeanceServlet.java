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

import static java.lang.Integer.parseInt;

@WebServlet("/account/edit_seance")
public class EditSeanceServlet extends HttpServlet {
    SeanceDao seanceDao = null;
    FilmDao filmDao = null;

    @Override
    public void init() throws ServletException {
        seanceDao = new SeanceDao();
        filmDao = new FilmDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            showEditForm(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/account");
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
        System.out.println(updateSeance.toString());
        seanceDao.updateSeance(updateSeance);
        response.sendRedirect(request.getContextPath() + "/account/seances_list");
    }
}
