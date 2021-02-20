package com.finalProject.kuleshov.cinema.dao;

import com.finalProject.kuleshov.cinema.entity.Ticket;

import java.util.List;

public interface TicketDao {

    /**
     * find amount per seances for the period
     * @param period
     * @return
     */
    List<Ticket> findAmountForPeriod(String period);

    /**
     * select all movies order by popularity
     * @return
     */
    List<Ticket> findAllMovieByPopularity(String period);

    /**
     * delete ticket by id
     * @param id
     * @return
     */
    boolean deleteTicketById(int id);

    /**
     * find all user tickets
     * @param userId
     * @return
     */
    List<Ticket> findAllUserTickets(int userId);

    /**
     * select occupied places by seanceId
     * @param seanceId
     * @return
     */
    List<Integer> selectOccupiedPlaces(int seanceId);

    /**
     * buy tickets
     * @param tickets
     * @return
     */
    boolean buyTicket(List<Ticket> tickets);


}
