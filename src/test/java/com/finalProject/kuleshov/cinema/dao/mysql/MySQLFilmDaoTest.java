package com.finalProject.kuleshov.cinema.dao.mysql;


import com.finalProject.kuleshov.cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.cinema.entity.Film;
import com.finalProject.kuleshov.cinema.entity.User;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;


public class MySQLFilmDaoTest {
    static Context context;


    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        ds.setURL("jdbc:mysql://localhost:3306/CinemaDB");
        ds.setUser("root");
        ds.setPassword("rootroot");
        DataSource dataSource = ds;
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
        context = new InitialContext();
        Context ctx = context.createSubcontext("java");
        ctx.createSubcontext("comp").createSubcontext("env").createSubcontext("jdbc")
                .bind("cinemaPool", dataSource);
        context.bind("java:", ctx);


    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.destroySubcontext("java");
        context.unbind("java:");
        context.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void selectFilmById() throws Exception {
        Film film = new MySQLFilmDao().selectFilmById(1);
        assertEquals("I Feel Pretty", film.getName());
    }

    @Test
    public void allFilmsShouldBeNotNullTest() throws Exception {
        List<Film> allFilms = new MySQLFilmDao().findAllFilms();
        assertNotNull(allFilms);
    }

    @Test
    public void selectFilmAndIdShouldBeNotNullTest() throws Exception {
        List<Film> allFilms = new MySQLFilmDao().selectFilmIdName();
        assertNotNull(allFilms);
    }

    @Test
    public void updateFilmTest() throws Exception {
        Film film = new MySQLFilmDao().selectFilmById(2);
        film.setImg("escape.jpeg");

        boolean actual = new MySQLFilmDao().updateFilm(film);
        assertTrue(actual);

    }

    @Test
    public void addFilmTest() throws Exception {
        Film film = new Film();
        film.setName("filmTest");
        new MySQLFilmDao().addFilm(film);
    }

    @Test
    public void deleteFilmTest() throws Exception {
        List<Film> allFilms = new MySQLFilmDao().findAllFilms();
        boolean actual = new MySQLFilmDao().deleteFilmById(allFilms.get(0).getId());
        assertTrue(actual);
    }
}