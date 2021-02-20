package com.finalProject.kuleshov.cinema.controller.admin;

import com.finalProject.kuleshov.cinema.dao.SeanceDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.cinema.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/delete_seance")
public class DeleteSeanceServlet extends HttpServlet {
    private SeanceDao seanceDao = null;

    @Override
    public void init() throws ServletException {
        seanceDao = new MySQLSeanceDao();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User.ROLE role = (User.ROLE) request.getSession().getAttribute("role");
        if (role.equals(User.ROLE.ADMIN)) {
            deleteSeance(request, response);
        }
    }
    private void deleteSeance(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        seanceDao.deleteSeanceById(id);
        response.sendRedirect(request.getContextPath() + "/seances_list");
    }
}
