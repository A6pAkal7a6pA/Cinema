package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDao {
    Connection connection = null;

    public List<Film> selectFilmIdName() {
        Statement statement = null;
        ResultSet rs = null;
        List<Film> films = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQLConstants.SELECT_FILM_ID_NAME);
            while (rs.next()) {
                Film film = new Film();
                films.add(film);
                film.setId(rs.getInt("id"));
                film.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Trouble with selectFilmIdName");
        } finally {
            Util.close(statement, rs);
        }
        return films;
    }

    public void addFilm(Film film) {
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
        } catch (SQLException e) {
            System.out.println("Trouble with addFilm: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
    }

    public boolean updateFilm(Film film) {
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_FILM_BY_ID);
            ps.setString(1, film.getName());
            ps.setString(2, film.getDirectedBy());
            ps.setString(3, film.getDescription());
            ps.setInt(4, film.getDuration());
            ps.setInt(5, film.getId());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Trouble with updateFilm: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
        return result;
    }

    public Film selectFilmById(int id) {
        Film film = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_FILM_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String director = rs.getString("directedBy");
                String description = rs.getString("description");
                int duration = rs.getInt("duration");
                film = new Film(id, name, director, description, duration);
            }
        } catch (SQLException e) {
            System.out.println("Trouble with selectFilmById: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return film;
    }

    public boolean deleteFilmById(int id) {
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_FILM_BY_ID);
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Trouble with deleteFilmById: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
        return result;
    }

    public List<Film> findAllFilms() {
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
        } catch (SQLException e) {
            System.out.println("Trouble with findAllFilms: " + e.getMessage());
        } finally {
            Util.close(statement, rs);
        }
        return films;
    }
}
