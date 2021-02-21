package com.finalProject.kuleshov.cinema.dao.mysql;

import com.finalProject.kuleshov.cinema.entity.Film;
import com.finalProject.kuleshov.cinema.entity.Seance;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.apache.log4j.PropertyConfigurator;
import org.apache.naming.java.javaURLContextFactory;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import sun.util.resources.LocaleData;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class MySQLSeanceDaoTest {
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
    public void selectSeanceByIdTest() throws Exception {
        Seance seance = new MySQLSeanceDao().selectSeanceById(1);

        assertNotNull(seance);
    }

    @Test
    public void showSeanceByIdTest() throws Exception {
        Seance seance = new MySQLSeanceDao().showSeanceById(1);

        assertNotNull(seance);
    }

    @Test
    public void findAllSeanceByFilmIdTest() throws Exception {
        List<Seance> seances = new MySQLSeanceDao().findAllSeanceByFilmId(1);
        List<Seance> result = new ArrayList<>();
        int count = 0;
        for (Seance s : seances) {
            if (s.getFilmId() == 1) {
                result.add(s);
            }
            count++;
        }
        assertEquals(seances.size(), result.size());
    }

    @Test
    public void selectScheduleByCurrentDayTest() throws Exception {
        String currentDay = new MySQLSeanceDao().selectCurrentDay();
        List<Seance> seances = new MySQLSeanceDao().selectScheduleByDay(currentDay);
        assertNotNull(seances);
    }

    @Test
    public void showAllSeanceMustBe4SizeTest() throws Exception {
        List<Seance> seances = new MySQLSeanceDao().showAllSeance(null, 1, 4);
        assertEquals(4, seances.size());
    }

    @Test
    public void selectAndUpdateSeanceTest() throws Exception {
        Seance seance = new MySQLSeanceDao().selectSeanceById(1);
        boolean actual = new MySQLSeanceDao().updateSeance(seance);
        assertTrue(actual);
    }

    @Test
    public void addSeanceTest() throws Exception {
        Seance seance = new Seance();
        seance.setFilmId(1);
        seance.setDate("2021-03-20");
        seance.setPriceSeance(200);
        seance.setTimeSeance("12:00");
        new MySQLSeanceDao().addSeance(seance);
    }

    @Test
    public void deleteFilmTest() throws Exception {
        List<Seance> seances = new MySQLSeanceDao().showAllSeance();
        int max = 0;
        for (Seance s : seances) {
            if (s.getId() > max) {
                max = s.getId();
            }
        }
        boolean actual = new MySQLSeanceDao().deleteSeanceById(max);
        assertTrue(actual);
    }


}