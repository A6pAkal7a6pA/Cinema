package com.finalProject.kuleshov.cinema.dao.mysql;

import com.finalProject.kuleshov.cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.cinema.constants.SQLConstants;
import com.finalProject.kuleshov.cinema.dao.TicketDao;
import com.finalProject.kuleshov.cinema.entity.Ticket;
import com.finalProject.kuleshov.cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.finalProject.kuleshov.cinema.constants.Constants.*;

public class MySQLTicketDao implements TicketDao {
    private static final Logger LOG = Logger.getLogger(MySQLTicketDao.class);

    @Override
    public Ticket findOccupiedPlacesForYear() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ticket ticket = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.FIND_ALL_OCCUPIED_PLACES);
            rs = ps.executeQuery();
            if (rs.next()) {
                ticket = new Ticket();
                ticket.setAllOccupiedPlaces(rs.getInt("occupied_places"));
                ticket.setFirstHalfDay(rs.getInt("before_half_day"));
                ticket.setSecondHalfDay(rs.getInt("after_half_day"));
            }
        } catch (SQLException e) {
            LOG.error("Trouble with findOccupiedPlacesForYear: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return ticket;
    }

    public List<Ticket> findAmountForPeriod(String period) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.FIND_SUM_PER_SEANCE + "where " + period + "(s.date_seance) = " + period + "(now()) group by s.date_seance;");
            rs = ps.executeQuery();
            while (rs.next()) {
                ticket = new Ticket();
                tickets.add(ticket);
                ticket.setDateSeance(rs.getString(DATE_SEANCE));
                ticket.setSumPrice(rs.getInt(SUM_PRICE));
            }
        } catch (SQLException e) {
            LOG.error("Trouble with findAmountForPeriod: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return tickets;
    }

    @Override
    public List<Ticket> findAllMovieByPopularity(String period) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.FIND_MOVIE_BY_POPULARITY + " where " + period + "(s.date_seance) = " + period + "(now()) " +
                    "group by film_name " +
                    "order by purchased desc, sum_price desc;");
            rs = ps.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                ticket = new Ticket();
                tickets.add(ticket);
                ticket.setFilmName(rs.getString(FILM_NAME));
                ticket.setPurchasedPlaces(rs.getInt(PURCHASED_PLACES));
                ticket.setSumPrice(rs.getInt(SUM_PRICE));
            }
        } catch (SQLException e) {
            LOG.error("Trouble with findAllMovieByPopularity: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return tickets;
    }

    @Override
    public boolean deleteTicketById(int id) {
        Connection connection = null;
        boolean result = false;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.DELETE_TICKET_BY_ID);
            ps.setInt(1, id);
            result = ps.executeUpdate() > 0;
            LOG.info("deleteTicketById done");
        } catch (SQLException e) {
            LOG.error("Trouble with deleteTicketById: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
        return result;
    }

    @Override
    public List<Ticket> findAllUserTickets(int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> tickets = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_ALL_TICKETS_USER);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                tickets.add(ticket);
                ticket.setId(rs.getInt(ID));
                ticket.setFilmName(rs.getString(MOVIE_TITLE));
                ticket.setDateSeance(rs.getString(DATE_SEANCE));
                ticket.setTimeSeance(rs.getString(TIME_SEANCE));
                ticket.setNumberSeat(rs.getInt(PLACE));
                ticket.setPriceSeance(rs.getDouble(PRICE));
            }
            LOG.info("findAllUserTickets done");
        } catch (SQLException e) {
            LOG.error("Trouble with findAllUserTickets: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return tickets;
    }

    @Override
    public boolean buyTicket(List<Ticket> tickets) {
        boolean result = false;
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            Util.setAutocommit(connection, false);
            for (Ticket ticket : tickets) {
                ps = connection.prepareStatement(SQLConstants.INSERT_TICKET);
                ps.setInt(1, ticket.getUserId());
                ps.setInt(2, ticket.getSeanceId());
                ps.setInt(3, ticket.getNumberSeat());
                ps.executeUpdate();
                result = true;
            }
            connection.commit();

            LOG.info("buyTicket done");
        } catch (SQLException e) {
            LOG.error("Trouble with buyTicket: " + e.getMessage());
            Util.rollback(connection);
            return false;
        } finally {
            Util.setAutocommit(connection, true);
            Util.close(ps, connection);
        }
        return result;
    }


    @Override
    public List<Integer> selectOccupiedPlaces(int seanceId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Integer> occupiedPlaces = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_OCCUPIED_PLACES);
            ps.setInt(1, seanceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                occupiedPlaces.add(rs.getInt(PLACE));
            }
            LOG.info("selectOccupiedPlaces done");
        } catch (SQLException e) {
            LOG.error("Trouble with selectOccupiedPlaces: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return occupiedPlaces;
    }

}
