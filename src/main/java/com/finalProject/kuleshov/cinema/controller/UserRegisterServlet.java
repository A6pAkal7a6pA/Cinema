package com.finalProject.kuleshov.cinema.controller;

import com.finalProject.kuleshov.cinema.dao.UserDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.cinema.entity.User;
import com.finalProject.kuleshov.cinema.util.Util;
import com.finalProject.kuleshov.cinema.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {
    private UserDao userDao;
    private String message = null;

    public UserRegisterServlet() {
    }

    @Override
    public void init() throws ServletException {
        userDao = new MySQLUserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/user_register.jsp").forward(req, resp);;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String contact = req.getParameter("contact");
        String email = req.getParameter("email");

        System.out.println(contact);
        System.out.println(email);

        if (!Validator.isValidLogin(login)) {
            message = "Invalid login";
            req.setAttribute("message" , message);
            this.doGet(req, resp);
            return;
        }

        if (!Validator.isValidPassword(password)) {
            message = "Invalid password";
            req.setAttribute("message" , message);
            this.doGet(req, resp);
            return;
        }

        if (contact != null && contact != "" &&  !Validator.isValidPhoneNumber(contact)) {
            message = "Invalid phone number";
            req.setAttribute("message" , message);
            this.doGet(req, resp);
            return;
        }

        if (email != null && email != "" && !Validator.isValidEmail(email)) {
            message = "Invalid email";
            req.setAttribute("message" , message);
            this.doGet(req, resp);
            return;
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setLogin(login);
        user.setPassword(Util.hash(password, "MD5"));
        user.setContact(contact);
        user.setRole(User.ROLE.USER);
        user.setEmail(email);

        userDao.registerUser(user);

        resp.sendRedirect(req.getContextPath() + "/login");

    }
}
