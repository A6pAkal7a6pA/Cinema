package com.finalProject.kuleshov.Cinema.dao.mysql;

import com.finalProject.kuleshov.Cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.Cinema.constants.SQLConstants;
import com.finalProject.kuleshov.Cinema.dao.UserDao;
import com.finalProject.kuleshov.Cinema.entity.User;
import com.finalProject.kuleshov.Cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDao implements UserDao {
//    Connection connection = null;
    private static final Logger LOG = Logger.getLogger(MySQLUserDao.class);

    @Override
    public int registerUser(User user) {
        int result = 0;
        Connection connection = null;
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
            LOG.info("registerUser done");
        } catch (SQLException e) {
            LOG.error("Trouble with registerUser: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, connection);
        }
        return result;
    }

    @Override
    public User logIn(String login, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        User user = null;
        ResultSet rs = null;
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
                user.setPassword(rs.getString("password"));
                user.setContact(rs.getString("contact"));
            }
            LOG.info("logIn done");
        } catch (SQLException e) {
            LOG.error("Trouble with logIn: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        Connection connection = null;
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
            LOG.info("findAllUsers done");
        } catch (SQLException e) {
            LOG.error("Trouble with findAllUser: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(statement, rs, connection);
        }
        return users;
    }

    @Override
    public User selectUserByLogin(String login) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_USER_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setContact(rs.getString("contact"));
                user.setEmail(rs.getString("email"));
                user.setDate(rs.getDate("reg_date_user"));
                return user;
            }
            LOG.info("selectUserByLogin done");
        } catch (SQLException e) {
            LOG.error("Trouble with selectUserByLogin: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return null;
    }

    @Override
    public boolean checkUser(User user) {
        Connection connection = null;
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
            LOG.info("checkUser done");
        } catch (SQLException e) {
            LOG.error("Trouble with checkUser: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return status;
    }

    @Override
    public User checkAccess(String login, String password) {
        Connection connection = null;
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
            LOG.info("checkAccess done");
        } catch (SQLException e) {
            LOG.error("Trouble with checkAccess: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return user;
    }

    @Override
    public User.ROLE getRoleByLoginPassword(String login, String password) {
        Connection connection = null;
        User.ROLE result = User.ROLE.UNKNOWN;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_ROLE_BY_LOGIN_PASSWORD);
            ps.setString(1, login);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setRole(User.ROLE.valueOf(rs.getString("role")));
                result = user.getRole();
            }
            LOG.info("getRoleByLoginPassword done");
        } catch (SQLException e) {
            LOG.error("Trouble with getRoleByLoginPassword: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return result;
    }

}


