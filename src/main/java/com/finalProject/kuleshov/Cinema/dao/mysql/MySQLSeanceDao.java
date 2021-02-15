package com.finalProject.kuleshov.Cinema.dao.mysql;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.dao.SeanceDao;
import com.finalProject.kuleshov.Cinema.dto.Seance;
import com.finalProject.kuleshov.Cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLSeanceDao implements SeanceDao {
    private static final Logger LOG = Logger.getLogger(MySQLSeanceDao.class);
    Connection connection = null;

    @Override
    public String selectCurrentDay() {
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
        } finally {
            Util.close(ps);
        }
        return result;
    }

    @Override
    public List<Seance> selectScheduleByDay(String day) {
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
        } finally {
            Util.close(ps, rs);
        }
        return seances;
    }

    @Override
    public Seance showSeanceById(int id) {
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
        } finally {
            Util.close(ps, rs);
        }
        return seance;
    }

    @Override
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
            LOG.info("updateSeance done");
        } catch (SQLException e) {
            LOG.error("Trouble with updateSeance: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
        return result;
    }

    @Override
    public Seance selectSeanceById(int id) {
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
            System.out.println("Trouble with selectSeanceById: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return seance;
    }

    @Override
    public boolean deleteSeanceById(int id) {
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
        } finally {
            Util.close(ps);
        }
        return result;
    }

    @Override
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
            LOG.info("addSeance done");
        } catch (SQLException e) {
            LOG.error("Trouble with addSeance: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
    }

    @Override
    public String sortingOption(String sortRequest) {
        if (sortRequest == null) {
            return "order by 4 asc, 5 asc ";
        }
        String orderByDateTimeAsc = "order by 4 asc, 5 asc ";
        String orderByMovieTitleAsc = "order by 3 asc ";
        String orderByAvailableSeatsAsc = "order by free_places asc ";
        String orderByDateTimeDesc = "order by 4 desc, 5 desc ";
        String orderByMovieTitleDesc = "order by 3 desc ";
        String orderByAvailableSeatsDesc = "order by free_places desc ";
        String orderByPriceAsc = "order by 6 asc ";
        String orderByPriceDesc = "order by 6 desc ";
        switch (sortRequest) {
            case "dateTimeSortAsc":
                return orderByDateTimeAsc;
            case "availableSeatsSortAsc":
                return orderByAvailableSeatsAsc;
            case "movieTitleSortAsc":
                return orderByMovieTitleAsc;
            case "dateTimeSortDesc":
                return orderByDateTimeDesc;
            case "availableSeatsSortDesc":
                return orderByAvailableSeatsDesc;
            case "movieTitleSortDesc":
                return orderByMovieTitleDesc;
            case "priceSortAsc":
                return orderByPriceAsc;
            case "priceSortDesc":
                return orderByPriceDesc;
            default:
                return "order by 4 asc, 5 asc ";
        }
    }

    @Override
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
                seance.setFreePlaces(rs.getInt("free_places"));
            }
            LOG.info("showAllSeance done");
        } catch (SQLException e) {
            LOG.error("Trouble with showAllSeance: " + e.getMessage());
        } finally {
            Util.close(statement, rs);
        }
        return seances;
    }

    @Override
    public List<Seance> showAllSeance(String sortRequest, int start, int total) {
        List<Seance> seances = new ArrayList<>();
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(
                    SQLConstants.SELECT_ALL_SEANCES +
                            sortingOption(sortRequest) + " limit " + (start - 1) + ", " + total);
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
        } finally {
            Util.close(statement, rs);
        }
        return seances;
    }

}
