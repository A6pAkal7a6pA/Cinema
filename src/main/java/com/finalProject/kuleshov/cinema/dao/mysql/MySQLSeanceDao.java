package com.finalProject.kuleshov.cinema.dao.mysql;

import com.finalProject.kuleshov.cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.cinema.constants.SQLConstants;
import com.finalProject.kuleshov.cinema.dao.SeanceDao;
import com.finalProject.kuleshov.cinema.entity.Seance;
import com.finalProject.kuleshov.cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.finalProject.kuleshov.cinema.constants.Constants.*;


public class MySQLSeanceDao implements SeanceDao {
    private static final Logger LOG = Logger.getLogger(MySQLSeanceDao.class);


    @Override
    public List<Seance> findAllSeanceByFilmId(int filmId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Seance> seances = new ArrayList<>();
        Seance seance = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_ALL_SEANCES_BY_FILM_ID);
            ps.setInt(1, filmId);
            rs = ps.executeQuery();
            while (rs.next()) {
                seance = new Seance();
                seances.add(seance);
                seance.setId(rs.getInt(ID));
                seance.setFilmName(rs.getString(FILM_NAME));
                seance.setFilmId(rs.getInt(FILM_ID));
                seance.setDayName(rs.getString(DAY_NAME));
                seance.setDate(rs.getString(DATE_SEANCE));
                seance.setTimeSeance(rs.getString(TIME_SEANCE));
            }
            LOG.info("findAllSeanceByFilmId done");
        } catch (SQLException e) {
            LOG.error("Trouble with findAllSeanceByFilmId: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return seances;
    }

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
                result = rs.getString(DAY_NAME);
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
                seance.setId(rs.getInt(ID));
                seance.setFilmName(rs.getString(FILM_NAME));
                seance.setFilmId(rs.getInt(FILM_ID));
                seance.setDayName(rs.getString(DAY_NAME));
                seance.setDate(rs.getString(DATE_SEANCE));
                seance.setTimeSeance(rs.getString(TIME_SEANCE));
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
                seance.setId(rs.getInt(ID));
                seance.setImg(rs.getString(PHOTO_FILM));
                seance.setFilmName(rs.getString(FILM_NAME));
                seance.setDate(rs.getString(DATE_SEANCE));
                seance.setTimeSeance(rs.getString(TIME_SEANCE));
                seance.setPriceSeance(rs.getDouble(PRICE));
                seance.setNumberOfSeats(rs.getInt(NUMBER_OF_SEATS));
                seance.setDuration(rs.getInt(DURATION));
                seance.setDirector(rs.getString(DIRECTOR));
                seance.setDescription(rs.getString(DESCRIPTION));
                seance.setFilmId(rs.getInt(FILM_ID));
                seance.setFreePlaces(rs.getInt(FREE_PLACES));
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
                String fileName = rs.getString(FILM_NAME);
                String dateSeance = rs.getString(DATE_SEANCE);
                String timeSeance = rs.getString(TIME_SEANCE);
                double price = rs.getDouble(PRICE);
                int filmId = rs.getInt(FILM_ID);
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
            rs = statement.executeQuery(SQLConstants.SELECT_ALL_SEANCES_ADM);
            while (rs.next()) {
                Seance seance = new Seance();
                seances.add(seance);
                seance.setId(rs.getInt(ID));
                seance.setImg(rs.getString(PHOTO_FILM));
                seance.setFilmName(rs.getString(FILM_NAME));
                seance.setDate(rs.getString(DATE_SEANCE));
                seance.setTimeSeance(rs.getString(TIME_SEANCE));
                seance.setPriceSeance(rs.getDouble(PRICE));
                seance.setNumberOfSeats(rs.getInt(NUMBER_OF_SEATS));
                seance.setDuration(rs.getInt(DURATION));
                seance.setDirector(rs.getString(DIRECTOR));
                seance.setDescription(rs.getString(DESCRIPTION));
                seance.setFilmId(rs.getInt(FILM_ID));
                seance.setFreePlaces(rs.getInt(FREE_PLACES));
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
                seance.setId(rs.getInt(ID));
                seance.setImg(rs.getString(PHOTO_FILM));
                seance.setFilmName(rs.getString(FILM_NAME));
                seance.setDate(rs.getString(DATE_SEANCE));
                seance.setTimeSeance(rs.getString(TIME_SEANCE));
                seance.setPriceSeance(rs.getDouble(PRICE));
                seance.setNumberOfSeats(rs.getInt(NUMBER_OF_SEATS));
                seance.setDuration(rs.getInt(DURATION));
                seance.setDirector(rs.getString(DIRECTOR));
                seance.setDescription(rs.getString(DESCRIPTION));
                seance.setFilmId(rs.getInt(FILM_ID));
                seance.setFreePlaces(rs.getInt(FREE_PLACES));
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
