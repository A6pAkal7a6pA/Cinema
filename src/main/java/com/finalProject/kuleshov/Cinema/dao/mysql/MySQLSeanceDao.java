package com.finalProject.kuleshov.Cinema.dao.mysql;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.entity.Seance;
import com.finalProject.kuleshov.Cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLSeanceDao implements SeanceDao {
    private static final Logger LOG = Logger.getLogger(MySQLSeanceDao.class);

    @Override
    public String selectCurrentDay() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_CURRENT_DAY);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("day_name");
            }
            LOG.info("selectCurrentDay done");
        } catch (SQLException e) {
            LOG.error("Trouble with selectCurrentDay: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
        return result;
    }

    @Override
    public List<Seance> selectScheduleByDay(String day) {
        Connection connection = null;
        List<Seance> seances = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Seance seance = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_SEANCES_BY_DAY);
            ps.setString(1, day);
            rs = ps.executeQuery();
            while (rs.next()) {
                seance = new Seance();
                seances.add(seance);
                seance.setId(rs.getInt("id"));
                seance.setFilmName(rs.getString("film_name"));
                seance.setFilmId(rs.getInt("film_id"));
                seance.setDayName(rs.getString("day_name"));
                seance.setDate(rs.getString("date_seance"));
                seance.setTimeSeance(rs.getString("time_seance"));
            }
            LOG.info("selectScheduleByDay done");
        } catch (SQLException e) {
            LOG.error("Trouble with selectScheduleByDay: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return seances;
    }

    @Override
    public Seance showSeanceById(int id) {
        Connection connection = null;
        Seance seance = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_SEANCE_BY_ID_JOIN);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                seance = new Seance();
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
                seance.setFreePlaces(rs.getInt("free_places"));
            }
            LOG.info("showSeanceById done");
        } catch (SQLException e) {
            LOG.error("Trouble with showSeanceById: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return seance;
    }

    @Override
    public boolean updateSeance(Seance seance) {
        Connection connection = null;
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
            LOG.info("updateSeance done");
        } catch (SQLException e) {
            LOG.error("Trouble with updateSeance: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
        return result;
    }

    @Override
    public Seance selectSeanceById(int id) {
        Connection connection = null;
        Seance seance = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_SEANCE_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String fileName = rs.getString("film_name");
                String dateSeance = rs.getString("date_seance");
                String timeSeance = rs.getString("time_s");
                double price = rs.getDouble("price");
                int filmId = rs.getInt("film_id");
                seance = new Seance(id, filmId, dateSeance, timeSeance, price, fileName);
            }
            LOG.info("selectSeanceById done");
        } catch (SQLException e) {
            LOG.error("Trouble with selectSeanceById: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return seance;
    }

    @Override
    public boolean deleteSeanceById(int id) {
        Connection connection = null;
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_SEANCE_BY_ID);
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
            LOG.info("deleteSeanceById done");
        } catch (SQLException e) {
            LOG.error("Trouble with deleteSeanceById: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
        return result;
    }

    @Override
    public void addSeance(Seance seance) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_SEANCE);
            ps.setInt(1, seance.getFilmId());
            ps.setString(2, seance.getDate());
            ps.setString(3, seance.getTimeSeance());
            ps.setDouble(4, seance.getPriceSeance());
            ps.executeUpdate();
            LOG.info("addSeance done");
        } catch (SQLException e) {
            LOG.error("Trouble with addSeance: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
    }

    @Override
    public List<Seance> showAllSeance() {
        Connection connection = null;
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
                seance.setFreePlaces(rs.getInt("free_places"));
            }
            LOG.info("showAllSeance done");
        } catch (SQLException e) {
            LOG.error("Trouble with showAllSeance: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(statement, rs, connection);
        }
        return seances;
    }

    @Override
    public List<Seance> showAllSeance(String sortRequest, int start, int total) {
        Connection connection = null;
        List<Seance> seances = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(
                    SQLConstants.SELECT_ALL_SEANCES +
                            Util.sortingOption(sortRequest) + " limit " + (start - 1) + ", " + total);
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
                seance.setFreePlaces(rs.getInt("free_places"));
            }
            LOG.info("showAllSeance done");
        } catch (SQLException e) {
            LOG.error("Trouble with showAllSeance: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(statement, rs, connection);
        }
        return seances;
    }

}
