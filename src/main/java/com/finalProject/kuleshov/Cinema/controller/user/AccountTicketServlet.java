package com.finalProject.kuleshov.Cinema.controller.user;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dao.TicketDao;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLSeanceDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLTicketDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.Cinema.entity.Ticket;
import com.finalProject.kuleshov.Cinema.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/my_ticket")
public class AccountTicketServlet extends HttpServlet {
    FilmDao filmDao = null;
    UserDao userDao = null;
    SeanceDao scheduleDao = null;
    TicketDao ticketDao = null;

    @Override
    public void init() throws ServletException {
        filmDao = new MySQLFilmDao();
        userDao = new MySQLUserDao();
        scheduleDao = new MySQLSeanceDao();
        ticketDao = new MySQLTicketDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showProfileUser(req, resp);
    }

    private void showProfileUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String login = (String) request.getSession().getAttribute("login");
        User user = userDao.selectUserByLogin(login);
        List<User> users = new ArrayList<>();
        users.add(user);
        request.setAttribute("userName", user.getFirstName());
        request.setAttribute("userProfile", users);
        showUserTickets(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/user/user_menu.jsp");
        dispatcher.forward(request, response);
    }

    private void showUserTickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("id");
        List<Ticket> allUserTickets = ticketDao.findAllUserTickets(userId);
        req.setAttribute("tickets", allUserTickets);
    }

}
