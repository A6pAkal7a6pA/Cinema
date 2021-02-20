package com.finalProject.kuleshov.cinema.controller.user;

import com.finalProject.kuleshov.cinema.dao.TicketDao;
import com.finalProject.kuleshov.cinema.dao.UserDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLTicketDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.cinema.entity.Ticket;
import com.finalProject.kuleshov.cinema.entity.User;

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
    private UserDao userDao = null;
    private TicketDao ticketDao = null;

    @Override
    public void init() throws ServletException {
        userDao = new MySQLUserDao();
        ticketDao = new MySQLTicketDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showProfileUser(req, resp);
    }

    private void showProfileUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("login");
        User user = userDao.selectUserByLogin(login);
        List<User> users = new ArrayList<>();
        users.add(user);
        request.setAttribute("userName", user.getFirstName());
        request.setAttribute("userProfile", users);

        int userId = (int) request.getSession().getAttribute("id");
        List<Ticket> allUserTickets = ticketDao.findAllUserTickets(userId);
        request.setAttribute("tickets", allUserTickets);

        request.getRequestDispatcher("/WEB-INF/view/user/user_menu.jsp").forward(request, response);
    }

}
