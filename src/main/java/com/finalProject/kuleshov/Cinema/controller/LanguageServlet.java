package com.finalProject.kuleshov.Cinema.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/change_lang")
public class LanguageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String language = req.getParameter("language");
        System.out.println("language " + language);
        if (language.equals("ru") || language.equals("en")) {
            System.out.println(language);
            session.setAttribute("locale", language);
        }
        req.getRequestDispatcher( "/").forward(req, resp);
    }
}
