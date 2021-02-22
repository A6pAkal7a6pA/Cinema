package com.finalProject.kuleshov.cinema.dao.mysql;

import com.finalProject.kuleshov.cinema.constants.SQLConstants;
import com.finalProject.kuleshov.cinema.entity.Seance;
import com.finalProject.kuleshov.cinema.entity.Ticket;
import com.finalProject.kuleshov.cinema.entity.User;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MySQLTicketDaoTest {

    static Context context;


    @BeforeClass
    public static void setupBeforeClass() throws Exception {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        ds.setURL("jdbc:mysql://localhost:3306/CinemaDB");
        ds.setUser("root");
        ds.setPassword("rootroot");
        DataSource dataSource = ds;
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, javaURLContextFactory.class.getName());
        context = new InitialContext();
        Context ctx = context.createSubcontext("java");
        ctx.createSubcontext("comp").createSubcontext("env").createSubcontext("jdbc")
                .bind("cinemaPool", dataSource);
        context.bind("java:", ctx);


    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        context.destroySubcontext("java");
        context.unbind("java:");
        context.close();
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void selectOccupiedPlacesTest() throws Exception {
        List<Integer> integers = new MySQLTicketDao().selectOccupiedPlaces(1);
        assertNotNull(integers);
    }

    @Test
    public void buyTicketAndDeleteTicketTest() throws Exception {
        List<Ticket> allUserTickets = new MySQLTicketDao().findAllUserTickets(16);
        int actualPlace = 0;
        for (Ticket t : allUserTickets) {
            actualPlace = t.getId();
        }
        List<Ticket> tickets = new ArrayList<>();
        Ticket ticket = new Ticket();
        tickets.add(ticket);

        ticket.setSeanceId(13);
        ticket.setUserId(16);
        ticket.setNumberSeat(18);
        boolean deleteTicketActual = new MySQLTicketDao().deleteTicketById(actualPlace);
        assertTrue(deleteTicketActual);
        boolean buyTicketActual = new MySQLTicketDao().buyTicket(tickets);
        assert(buyTicketActual);
    }

    @Test
    public void findAllMoviesByPopularityTest() throws Exception {
        List<Ticket> findAllMoviesByPopularity = new MySQLTicketDao().findAllMovieByPopularity("year");
        assertNotNull(findAllMoviesByPopularity);
    }

    @Test
    public void findOccupiedPlacesForYearTest() throws Exception {
        Ticket occupiedPlacesForYear = new MySQLTicketDao().findOccupiedPlacesForYear();
        assertNotNull(occupiedPlacesForYear);
    }

    @Test
    public void selectOccupiedPlaces() throws Exception {
        List<Integer> integers = new MySQLTicketDao().selectOccupiedPlaces(13);
        assertTrue(integers.size() > 0);
    }

    @Test
    public void findTotalAmountByPeriodTest() throws Exception {
        Ticket year = new MySQLTicketDao().findTotalAmountByPeriod("year");
        assertNotNull(year);
    }

    @Test
    public void findAmountForPeriodTest() throws Exception {
        List<Ticket> day = new MySQLTicketDao().findAmountForPeriod("day");
        assertNotNull(day);
    }

    @Test
    public void cannotBuyTicketWhichAlreadyBought() throws Exception {
        User userOne = new MySQLUserDao().findUserById(3);
        User userTwo = new MySQLUserDao().findUserById(16);
        List<Ticket> userOneTickets = new ArrayList<>();
        List<Ticket> userTwoTickets = new ArrayList<>();

        Ticket ticketOne = new Ticket();
        ticketOne.setUserId(userOne.getId());
        ticketOne.setSeanceId(13);
        ticketOne.setNumberSeat(4);
        userOneTickets.add(ticketOne);

        Ticket ticketThree = new Ticket();
        ticketThree.setUserId(userTwo.getId());
        ticketThree.setSeanceId(13);
        ticketThree.setNumberSeat(5);
        userTwoTickets.add(ticketThree);

        Ticket ticketTwo = new Ticket();
        ticketTwo.setUserId(userOne.getId());
        ticketTwo.setSeanceId(13);
        ticketTwo.setNumberSeat(5);
        userOneTickets.add(ticketTwo);



        boolean actualTwo = new MySQLTicketDao().buyTicket(userTwoTickets);
        assertTrue(actualTwo);
        boolean actualOne = new MySQLTicketDao().buyTicket(userOneTickets);
        assertFalse(actualOne);

        List<Ticket> allUserTickets = new MySQLTicketDao().findAllUserTickets(userTwo.getId());
        System.out.println(allUserTickets.get(allUserTickets.size() - 1).getId());
        new MySQLTicketDao().deleteTicketById(allUserTickets.get(allUserTickets.size() - 1).getId());

    }

}