package com.finalProject.kuleshov.Cinema.servlet;

import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/add_new_film")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
public class AddFilmServlet extends HttpServlet {
    FilmDao filmDao = null;
    UserDao userDao = null;
    private final String UPLOAD_DIRECTORY = "images";

    @Override
    public void init() throws ServletException {
        filmDao = new FilmDao();
        userDao = new UserDao();
    }

    private String getFileName(Part part) {
        System.out.println("getFileName");
        System.out.println("content-disp: " + Arrays.toString(part.getHeader("content-disposition").split(";")));
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (!content.trim().startsWith("filename")) {
                continue;
            }
            System.out.println("content start: " + content.trim().startsWith("filename"));
            part.getSubmittedFileName();
            return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "4234234";
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User.ROLE role = (User.ROLE) req.getSession().getAttribute("role");

        if (role.equals(User.ROLE.ADMIN)) {
            showNewForm(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/account");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("start insert");

        String uploadPath = request.getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
        System.out.println(uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        System.out.println(uploadDir.exists());

        String fileName = "";
        for (Part part : request.getParts()) {
            System.out.println("part: " + part.getName());
            fileName = getFileName(part);
            System.out.println("fileName: " + fileName);
            part.write(uploadPath + File.separator + fileName);
        }

        String name = request.getParameter("title");
        System.out.println(name);
        String directedBy = request.getParameter("director");
        System.out.println(directedBy);
        String description = request.getParameter("description");
        System.out.println(description);
        int duration = Integer.parseInt(request.getParameter("duration"));
        Film newFilm = new Film(name, directedBy, description, duration, fileName);
        System.out.println(uploadPath + "/" + newFilm.getImg());

        System.out.println(newFilm.toString());
        filmDao.addFilm(newFilm);


        response.sendRedirect(request.getContextPath() + "/account");
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/film_form.jsp");
        dispatcher.forward(request, response);
    }


}
