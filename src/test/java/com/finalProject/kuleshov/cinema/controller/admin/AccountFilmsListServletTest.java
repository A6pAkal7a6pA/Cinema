package com.finalProject.kuleshov.cinema.controller.admin;

import com.finalProject.kuleshov.cinema.dao.FilmDao;
import com.finalProject.kuleshov.cinema.dao.mysql.MySQLFilmDao;
import com.finalProject.kuleshov.cinema.entity.Film;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AccountFilmsListServletTest {

//    private static final String path = "/WEB-INF/view/admin/films_list.jsp";
//
//    @Test
//    public void whenCallDoGetThenServletReturnAdminPage() throws ServletException, IOException {
//
//        final AccountFilmsListServlet servlet = new AccountFilmsListServlet();
//
//        final HttpServletRequest request = mock(HttpServletRequest.class);
//        final HttpServletResponse response = mock(HttpServletResponse.class);
//        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//        FilmDao filmDao = new MySQLFilmDao();
//        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
//        List<Film> allFilms = filmDao.findAllFilms();
//        request.setAttribute("listFilms", allFilms);
//        servlet.doGet(request, response);
//
//        verify(request, times(1)).getRequestDispatcher(path);
//        verify(request, never()).getSession();
//        verify(dispatcher).forward(request, response);
//    }

}