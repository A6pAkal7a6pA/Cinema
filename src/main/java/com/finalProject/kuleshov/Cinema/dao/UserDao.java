package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.model.User;
import com.finalProject.kuleshov.Cinema.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection connection = null;

    public int registerUser(User user) {
        System.out.println("registerUser");
        int result = 0;
        PreparedStatement ps = null;

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.INSERT_USER);
            ps.setInt(1, user.getId());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getLogin());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getContact());
            ps.setString(7, user.getEmail());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Trouble with registerUser: " + e.getMessage());
        } finally {
            Util.close(ps);
        }
        return result;
    }

    public User logIn(String login, String password) {
        System.out.println("Login User");
        PreparedStatement ps = null;
        User user = new User();
        ResultSet rs = null;
        connection = ConnectionPool.getInstance().getConnection();
        try {
            ps = connection.prepareStatement(SQLConstants.SELECT_ALL_USERS_BY_LOGIN_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setContact(rs.getString("contact"));
            }
        } catch (SQLException e) {
            System.out.println("Trouble with logIn: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return user;
    }

    public List<User> findAllUsers() {
        Statement statement = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try {
            connection = ConnectionPool.getInstance().getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQLConstants.SELECT_ALL_USER);
            while (rs.next()) {
                User user = new User();
                users.add(user);
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setContact(rs.getString("contact"));
                user.setRole(User.ROLE.valueOf(rs.getString("role")));
                user.setEmail(rs.getString("email"));
                user.setDate(rs.getDate("reg_date_user"));
            }
        } catch (SQLException e) {
            System.out.println("Trouble with findAllUser: " + e.getMessage());
        } finally {
            Util.close(statement, rs);
        }
        return users;
    }

    public User selectUserByLogin(String login) {
        connection = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQLConstants.SELECT_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setContact(rs.getString("contact"));
                user.setEmail(rs.getString("email"));
                user.setDate(rs.getDate("reg_date_user"));
                System.out.println(user.toString());
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Trouble with selectUserByLogin: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return null;
    }

    public boolean validate(User user) {
        boolean status = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_ALL_USERS_BY_LOGIN_PASSWORD);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            rs = ps.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            System.out.println("Trouble with validate: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return status;
    }

    public User checkAccess(String login, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_ALL_USERS_BY_LOGIN_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setLogin(rs.getString("login"));
                user.setContact(rs.getString("contact"));
                user.setRole(User.ROLE.valueOf(rs.getString("role")));
            }
        } catch (SQLException e) {
            System.out.println("Trouble with checkAccess: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return user;
    }

    public User.ROLE getRoleByLoginPassword(String login, String password) {
        User.ROLE result = User.ROLE.UNKNOWN;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();

        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_ROLE_BY_LOGIN_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setRole(User.ROLE.valueOf(rs.getString("role")));
                result = user.getRole();
            }
        } catch (SQLException e) {
            System.out.println("Trouble with getRoleByLoginPassword: " + e.getMessage());
        } finally {
            Util.close(ps, rs);
        }
        return result;
    }


    //        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CinemaDB?user=root&password=rootroot")) {
    public static void main(String[] args) {

    }

}


