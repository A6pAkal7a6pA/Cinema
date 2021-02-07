package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/account/edit_film")
public class EditFilmServlet extends HttpServlet {
    FilmDao filmDao = null;
    UserDao userDao= null;

    @Override
    public void init() throws ServletException {
        filmDao = new FilmDao();
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");
        System.out.println("editFilm goGet role: " + role);
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
            updateFilm(req, resp);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("id"));
        int id = parseInt(request.getParameter("id"));
        Film existingFilm = filmDao.selectFilmById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/film_form.jsp");
        request.setAttribute("film", existingFilm);
        dispatcher.forward(request, response);
    }

    private void updateFilm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        String name = request.getParameter("title");
        String directedBy = request.getParameter("director");
        String description = request.getParameter("description");
        int duration = parseInt(request.getParameter("duration"));

        Film updateFilm = new Film(id, name, directedBy, description, duration);
        filmDao.updateFilm(updateFilm);
        response.sendRedirect(request.getContextPath() + "/account/films_list");
    }
}
