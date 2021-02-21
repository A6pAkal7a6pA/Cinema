package com.finalProject.kuleshov.cinema.dao.mysql;

import com.finalProject.kuleshov.cinema.connection.ConnectionPool;
import com.finalProject.kuleshov.cinema.constants.SQLConstants;
import com.finalProject.kuleshov.cinema.dao.UserDao;
import com.finalProject.kuleshov.cinema.entity.User;
import com.finalProject.kuleshov.cinema.util.Util;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.finalProject.kuleshov.cinema.constants.Constants.*;

public class MySQLUserDao implements UserDao {
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
                user.setId(rs.getInt(ID));
                user.setFirstName(rs.getString(FIRST_NAME));
                user.setLastName(rs.getString(LAST_NAME));
                user.setLogin(rs.getString(LOGIN));
                user.setPassword(rs.getString(PASSWORD));
                user.setContact(rs.getString(CONTACT));
                user.setRole(User.ROLE.valueOf(rs.getString(ROLE)));
                user.setEmail(rs.getString(EMAIL));
                user.setDate(rs.getDate(REG_DATE_USER));
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
                user.setId(rs.getInt(ID));
                user.setFirstName(rs.getString(FIRST_NAME));
                user.setLastName(rs.getString(LAST_NAME));
                user.setContact(rs.getString(CONTACT));
                user.setEmail(rs.getString(EMAIL));
                user.setDate(rs.getDate(REG_DATE_USER));
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
            ps = connection.prepareStatement(SQLConstants.SELECT_USER_BY_LOGIN_PASSWORD);
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
    public User.ROLE getRoleByLogin(String login) {
        Connection connection = null;
        User.ROLE result = User.ROLE.UNKNOWN;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.SELECT_ROLE_BY_LOGIN);
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setRole(User.ROLE.valueOf(rs.getString(ROLE)));
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

    @Override
    public User findUserById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(SQLConstants.FIND_USER_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt(ID));
                user.setFirstName(rs.getString(FIRST_NAME));
                user.setLastName(rs.getString(LAST_NAME));
                user.setLogin(rs.getString(LOGIN));
                user.setContact(rs.getString(CONTACT));
                user.setEmail(rs.getString(EMAIL));
                user.setDate(rs.getDate(REG_DATE_USER));
            }
            LOG.info("findUserById done");
        } catch (SQLException e) {
            LOG.error("Trouble with findUserById: " + e.getMessage());
            Util.rollback(connection);
        } finally {
            Util.close(ps, rs, connection);
        }
        return user;
    }
}


