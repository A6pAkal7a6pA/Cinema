package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.entity.Ticket;

import java.util.List;

public interface TicketDao {

    public List<Ticket> findAllUserTickets(int userId);

    public int findFreePlacesBySeanceId(int seanceId);

    public void buyTicket(int userId, int seanceId, int place);

    public List<Integer> selectOccupiedPlaces(int seanceId);

    public boolean buyTicket(List<Ticket> tickets);


}
