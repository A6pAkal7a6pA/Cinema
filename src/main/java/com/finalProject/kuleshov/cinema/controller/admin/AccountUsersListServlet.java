package com.finalProject.kuleshov.cinema.controller.admin;

import com.finalProject.kuleshov.cinema.dao.TicketDao;
import com.finalProject.kuleshov.cinema.dao.UserDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLTicketDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.cinema.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users_list")
public class AccountUsersListServlet extends HttpServlet {
    private UserDao userDao = null;

    @Override
    public void init() throws ServletException {
        userDao = new MySQLUserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        showUsersList(req, resp);
    }

    private void showUsersList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userDao.findAllUsers();
        req.setAttribute("listUsers", allUsers);
        req.getRequestDispatcher("/WEB-INF/view/admin/users_list.jsp").forward(req, resp);
    }


}
