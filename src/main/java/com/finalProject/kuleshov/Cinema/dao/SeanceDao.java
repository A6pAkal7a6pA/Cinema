package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.model.Film;
import com.finalProject.kuleshov.Cinema.model.Seance;
import com.finalProject.kuleshov.Cinema.util.Util;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SeanceDao {
    Connection connection = null;

    public boolean updateSeance(Seance seance) {
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.UPDATE_SEANCE_BY_ID);
            ps.setInt(1, seance.getId());
            System.out.println("id: " + seance.getId());
            ps.setString(2, seance.getDate());
            ps.setString(3, seance.getTimeSeance());
            ps.setDouble(4, seance.getPriceSeance());
            ps.setInt(5, seance.getFilmId());
            ps.setInt(6, seance.getId());

            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Trouble with updateSeance: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
        return result;
    }

    public Seance selectSeanceById(int id) {
        Seance seance = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_SEANCE_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String fileName = rs.getString("film_name");
                String dateSeance = rs.getString("date_seance");
                String timeSeance = rs.getString("time_s");
                double price = rs.getDouble("price");
                int filmId = rs.getInt("film_id");
                seance = new Seance(id, filmId, dateSeance, timeSeance, price, fileName);
            }
        } catch (SQLException e) {
            System.out.println("Trouble with selectSeanceById: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return seance;
    }

    public boolean deleteSeanceById(int id) {
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_SEANCE_BY_ID);
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Trouble with deleteSeanceById: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
        return result;
    }

    public void addSeance(Seance seance) {
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_SEANCE);
            ps.setInt(1, seance.getFilmId());
            ps.setString(2, seance.getDate());
            ps.setString(3, seance.getTimeSeance());
            ps.setDouble(4, seance.getPriceSeance());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Trouble with addSeance: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
    }

    public List<Seance> showAllSeance() {
        List<Seance> seances = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQLConstants.SELECT_ALL_SEANCES);
            while (rs.next()) {
                Seance seance = new Seance();
                seances.add(seance);
                seance.setId(rs.getInt("id"));
                seance.setImg(rs.getString("photo_film"));
                seance.setFilmName(rs.getString("film_name"));
                seance.setDate(rs.getString("date_seance"));
                seance.setTimeSeance(rs.getString("time_seance"));
                seance.setPriceSeance(rs.getDouble("price"));
                seance.setNumberOfSeats(rs.getInt("number_of_seats"));
                seance.setDuration(rs.getInt("duration"));
                seance.setDirector(rs.getString("director"));
                seance.setDescription(rs.getString("description"));
                seance.setFilmId(rs.getInt("film_id"));

            }
        } catch (SQLException e) {
            System.out.println("Trouble with showAllSeance: " + e.getMessage());
        } finally {
            Util.close(statement, rs);
        }
        return seances;
    }


}
