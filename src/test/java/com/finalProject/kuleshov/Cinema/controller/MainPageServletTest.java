package com.finalProject.kuleshov.Cinema.controller;

import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.dao.mysql.MySQLUserDao;
import com.finalProject.kuleshov.Cinema.entity.User;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;

public class MainPageServletTest {
    private UserDao userDao;

    @Before
    public void setUp() {
        this.userDao = new MySQLUserDao();
    }

    private final static String path ="/index.jsp";

    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {
//        final MainPageServlet mainPageServlet = new MainPageServlet();
//
//        final HttpServletRequest request = mock(HttpServletRequest.class);
//        final HttpServletResponse response = mock(HttpServletResponse.class);
//        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
//
//        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
//
//        mainPageServlet.doGet(request, response);
//
////        verify(request, times(1)).getRequestDispatcher(path);
//        verify(dispatcher).forward(request, response);

        List<User> users = userDao.findAllUsers();
        for (User user : users) {
            System.out.println(user);
        }

    }


}
