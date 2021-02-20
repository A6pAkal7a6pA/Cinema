package com.finalProject.kuleshov.cinema.controller.user;

import com.finalProject.kuleshov.cinema.dao.TicketDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLTicketDao;
import com.finalProject.kuleshov.cinema.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;

@WebServlet("/delete_ticket")
public class DeleteTicketServlet extends HttpServlet {
    TicketDao ticketDao = null;

    @Override
    public void init() throws ServletException {
        ticketDao = new MySQLTicketDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = parseInt(request.getParameter("id"));
        ticketDao.deleteTicketById(id);
        response.sendRedirect(request.getContextPath() + "/my_ticket");
    }
}
