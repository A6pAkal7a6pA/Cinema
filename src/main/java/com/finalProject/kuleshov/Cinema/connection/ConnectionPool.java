package com.finalProject.kuleshov.Cinema.connection;

import com.finalProject.kuleshov.Cinema.util.Util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private ConnectionPool() {

    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/cinemaPool");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            System.out.println("Trouble with ConnectionPool: " + e.getMessage());
        }
        return connection;
    }
}
