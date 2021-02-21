package com.finalProject.kuleshov.cinema.connection;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    private ConnectionPool() {

    }

    private static ConnectionPool instance = null;

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() {
        Context context;
        Connection connection = null;
        try {
            context = new InitialContext();
            Context envContext = (Context) context.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/cinemaPool");
            connection = ds.getConnection();
        } catch (NamingException | SQLException e) {
            LOG.error("Trouble with getConnection: " + e.getMessage());
        }
        return connection;
    }
}
