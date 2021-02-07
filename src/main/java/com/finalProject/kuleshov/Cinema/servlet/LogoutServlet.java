package com.finalProject.kuleshov.Cinema.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    public LogoutServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("logout servlet doGet");


        HttpSession session = req.getSession();
        if (session != null) {
            session.removeAttribute("password");
            session.removeAttribute("login");
            session.removeAttribute("role");
            session.invalidate();
        }
        resp.sendRedirect(req.getContextPath() + "/login");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/user_login.jsp");
//        dispatcher.forward(req, resp);

        System.out.println("end logout");
    }
}
