package com.finalProject.kuleshov.cinema.dao.mysql;


import com.finalProject.kuleshov.cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.cinema.dao.UserDao;
import com.finalProject.kuleshov.cinema.entity.User;
import com.finalProject.kuleshov.cinema.util.Util;
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

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class MySQLUserDaoTest {
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
    public void findUserByIdTest() throws Exception {
        User userById = new MySQLUserDao().findUserById(3);
        assertEquals("Max", userById.getFirstName());
    }

    @Test
    public void selectUserByLoginTest() throws Exception {
        User user = new MySQLUserDao().selectUserByLogin("max123");
        assertEquals("Kul", user.getLastName());
    }
    @Test
    public void getRoleByLoginTest() throws Exception {
        User.ROLE role = new MySQLUserDao().getRoleByLogin("smaryna");
        assertEquals(User.ROLE.ADMIN, role);
    }

    @Test
    public void sameUserRegisterTest() throws Exception {
        User user = new User();
        user.setFirstName("Nikolay");
        user.setLastName("Ivanov");
        user.setLogin("userLogin");
        user.setPassword(Util.hash("userPassword", "MD5"));
        int i = new MySQLUserDao().registerUser(user);
        assertEquals(0, i);
    }

    @Test
    public void loginUserShouldReturnTrueTest() throws Exception {
        User user = new User();
        user.setFirstName("Nikolay");
        user.setLastName("Ivanov");
        user.setLogin("userLogin");
        user.setPassword(Util.hash("userPassword", "MD5"));
        boolean actual = new MySQLUserDao().checkUser(user);
        assertEquals(true, actual);
    }

    @Test
    public void shouldNotNullAllUsersTest() throws Exception {
        List<User> allUsers = new MySQLUserDao().findAllUsers();
        assertNotNull(allUsers);
    }
}