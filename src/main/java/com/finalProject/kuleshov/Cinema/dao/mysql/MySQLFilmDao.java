package com.finalProject.kuleshov.Cinema.dao.mysql;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.dao.FilmDao;
import com.finalProject.kuleshov.Cinema.entity.Film;
import com.finalProject.kuleshov.Cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                film.setId(rs.getInt("id"));
                film.setName(rs.getString("name"));
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
                String name = rs.getString("name");
                String director = rs.getString("directedBy");
                String description = rs.getString("description");
                int duration = rs.getInt("duration");
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
                film.setId(rs.getInt("id"));
                film.setName(rs.getString("name"));
                film.setDirectedBy(rs.getString("directedBy"));
                film.setDescription(rs.getString("description"));
                film.setDuration(rs.getInt("duration"));
                film.setDate(rs.getDate("reg_date"));
                film.setImg(rs.getString("picture"));
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
