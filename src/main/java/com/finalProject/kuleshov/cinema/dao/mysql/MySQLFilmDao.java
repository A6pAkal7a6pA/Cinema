package com.finalProject.kuleshov.cinema.dao.mysql;

import com.finalProject.kuleshov.cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.cinema.constants.SQLConstants;
import com.finalProject.kuleshov.cinema.dao.FilmDao;
import com.finalProject.kuleshov.cinema.entity.Film;
import com.finalProject.kuleshov.cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.finalProject.kuleshov.cinema.constants.Constants.*;

public class MySQLFilmDao implements FilmDao {
    private static final Logger LOG = Logger.getLogger(MySQLFilmDao.class);

    @Override
    public List<Film> selectFilmIdName() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Film> films = new ArrayList<>();
        Film film = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQLConstants.SELECT_FILM_ID_NAME);
            while (rs.next()) {
                film = new Film();
                films.add(film);
                film.setId(rs.getInt(ID));
                film.setName(rs.getString(NAME));
            }
            LOG.info("selectFilmIdName done");
        } catch (SQLException e) {
            LOG.error("Trouble with selectFilmIdName:" + e.getMessage());
        } finally {
            Util.close(statement, rs, connection);
        }
        return films;
    }

    @Override
    public void addFilm(Film film) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_FILM);
            ps.setString(1, film.getName());
            ps.setString(2, film.getDirectedBy());
            ps.setString(3, film.getDescription());
            ps.setInt(4, film.getDuration());
            ps.setString(5, film.getImg());
            ps.executeUpdate();
            LOG.info("addFilm done");
        } catch (SQLException e) {
            LOG.error("Trouble with addFilm: " + e.getMessage());
        } finally {
            Util.close(ps, connection);
        }
    }

    @Override
    public boolean updateFilm(Film film) {
        Connection connection = null;
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_FILM_BY_ID);
            ps.setString(1, film.getName());
            ps.setString(2, film.getDirectedBy());
            ps.setString(3, film.getDescription());
            ps.setInt(4, film.getDuration());
            ps.setString(5, film.getImg());
            ps.setInt(6, film.getId());
            result = ps.executeUpdate() > 0;
            LOG.info("updateFilm done");
        } catch (SQLException e) {
            LOG.error("Trouble with updateFilm: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
        return result;
    }

    @Override
    public Film selectFilmById(int id) {
        Connection connection = null;
        Film film = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_FILM_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString(NAME);
                String director = rs.getString(DIRECTED_BY);
                String description = rs.getString(DESCRIPTION);
                int duration = rs.getInt(DURATION);
                film = new Film(id, name, director, description, duration);
            }
            LOG.info("selectFilmById done");
        } catch (SQLException e) {
            LOG.error("Trouble with selectFilmById: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return film;
    }

    @Override
    public boolean deleteFilmById(int id) {
        Connection connection = null;
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_FILM_BY_ID);
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
            LOG.info("deleteFilmById done");
        } catch (SQLException e) {
            LOG.error("Trouble with deleteFilmById: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
        return result;
    }

    @Override
    public List<Film> findAllFilms() {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Film> films = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQLConstants.SELECT_ALL_FILMS);
            while (rs.next()) {
                Film film = new Film();
                films.add(film);
                film.setId(rs.getInt(ID));
                film.setName(rs.getString(NAME));
                film.setDirectedBy(rs.getString(DIRECTED_BY));
                film.setDescription(rs.getString(DESCRIPTION));
                film.setDuration(rs.getInt(DURATION));
                film.setDate(rs.getDate(REG_DATE));
                film.setImg(rs.getString(PICTURE));
            }
            LOG.info("findAllFilms done");
        } catch (SQLException e) {
            LOG.error("Trouble with findAllFilms: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(statement, rs, connection);
        }
        return films;
    }
}
