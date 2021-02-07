package com.finalProject.kuleshov.Cinema.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    public static void close(Statement statement, ResultSet rs) {
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Statement close() exception: " + e.getMessage());
        }
        try {
            rs.close();
        } catch (SQLException e) {
            System.out.println("ResultSet close() exception: " + e.getMessage());
        }
    }

    public static void close(Statement statement) {
        try {
            statement.close();
        } catch (SQLException e) {
            System.out.println("Statement close() exception: " + e.getMessage());
        }
    }

    public static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection close() exception: " + e.getMessage());
        }
    }
}
