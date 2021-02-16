package com.finalProject.kuleshov.Cinema.dao.mysql;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.dao.TicketDao;
import com.finalProject.kuleshov.Cinema.entity.Ticket;
import com.finalProject.kuleshov.Cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLTicketDao implements TicketDao {
    private static final Logger LOG = Logger.getLogger(MySQLTicketDao.class);

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
                ticket.setId(rs.getInt("id"));
                ticket.setFilmName(rs.getString("movie_title"));
                ticket.setDateSeance(rs.getString("date_seance"));
                ticket.setTimeSeance(rs.getString("time_seance"));
                ticket.setNumberSeat(rs.getInt("place"));
                ticket.setPriceSeance(rs.getDouble("price"));
            }
            LOG.info("findAllUserTickets done");
        } catch (SQLException e) {
            LOG.error("Trouble with findAllUserTickets: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs,connection);
        }
        return tickets;
    }

    @Override
    public int findFreePlacesBySeanceId(int seanceId) {
        Connection connection = null;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_FREE_PLACES);
            ps.setInt(1, seanceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("free_places");
            }
            LOG.info("findFreePlacesBySeanceId done");
        } catch (SQLException e) {
            LOG.error("Trouble with findFreePlacesBySeanceId: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return result;
    }

    @Override
    public void buyTicket(int userId, int seanceId, int place) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_TICKET);
            ps.setInt(1, userId);
            ps.setInt(2, seanceId);
            ps.setInt(3, place);
            ps.executeUpdate();
            LOG.info("buyTicket done");
        } catch (SQLException e) {
            LOG.error("Trouble with buyTicket: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
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
                occupiedPlaces.add(rs.getInt("number_seat"));
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
