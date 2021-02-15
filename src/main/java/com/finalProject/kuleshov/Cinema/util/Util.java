package com.finalProject.kuleshov.Cinema.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(date);
        return currentDate;
    }
}
