package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.model.Ticket;
import com.finalProject.kuleshov.Cinema.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {
    Connection connection = null;

    public int findFreePlacesBySeanceId(int seanceId) {
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
        } catch (SQLException e) {
            System.out.println("Trouble with findFreePlacesBySeanceId: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return result;
    }

    public List<Ticket> findTicketInfoBySeanceId(int seanceId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> ticketList = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_NEED_SEANCE_BY_ID);
            ps.setInt(1, seanceId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticketList.add(ticket);
                ticket.setImg(rs.getString("photo_film"));
                ticket.setFilmName(rs.getString("film_name"));
                ticket.setDateSeance(rs.getString("date_seance"));
                ticket.setTimeSeance(rs.getString("time_seance"));
                ticket.setPriceSeance(rs.getDouble("price"));
                ticket.setFreePlace(rs.getInt("free_place"));
                ticket.setAllPlace(rs.getInt("all_place"));
                ticket.setDuration(rs.getInt("duration"));
                ticket.setDirector(rs.getString("director"));
                ticket.setDescription(rs.getString("description"));
                ticket.setAllPlace(rs.getInt("number_of_seats"));
            }
        } catch (SQLException e) {
            System.out.println("Trouble with findInfoBySeanceId: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return ticketList;
    }

    public void buyTicket(int userId, int seanceId, int place) {
        PreparedStatement ps = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_TICKET);
            ps.setInt(1, userId);
            ps.setInt(2, seanceId);
            ps.setInt(3, place);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Trouble with buyTicket: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
    }

    public List<Integer> selectOccupiedPlaces(int seanceId) {
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
        } catch (SQLException e) {
            System.out.println("Trouble with selectOccupiedPlaces: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return occupiedPlaces;
    }

    public List<Ticket> findAllSeats() {
        Statement ps = null;
        ResultSet rs = null;
        List<Ticket> tickets = new ArrayList<>();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.createStatement();
            rs = ps.executeQuery(SQLConstants.SELECT_ALL_SEATS);
            while (rs.next()) {
                Ticket ticket = new Ticket();
                tickets.add(ticket);
                ticket.setNumberSeat(rs.getInt("number_seat"));
            }
        } catch (SQLException e) {
            System.out.println("Trouble with findAllTicket: " + e.getMessage());
        }
        return tickets;
    }

}
