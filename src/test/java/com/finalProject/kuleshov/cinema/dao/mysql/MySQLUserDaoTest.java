package com.finalProject.kuleshov.cinema.dao.mysql;

import com.finalProject.kuleshov.cinema.dao.UserDao;
import com.finalProject.kuleshov.cinema.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MySQLUserDaoTest {
    User user = new User();

    @Mock
    Connection mockCon;

    @Mock
    PreparedStatement mockPs;

    @Mock
    ResultSet mockRs;

    @Before
    public void setUp() throws SQLException {
        when(mockCon.prepareStatement(anyString())).thenReturn(mockPs);
        when(mockCon.prepareStatement(anyString(), anyInt())).thenReturn(mockPs);
        doNothing().when(mockPs).setInt(anyInt(), anyInt());
        doNothing().when(mockPs).setString(anyInt(), anyString());
        when(mockPs.executeUpdate()).thenReturn(1);
        when(mockPs.executeQuery()).thenReturn(mockRs);
        when(mockRs.next()).thenReturn(Boolean.FALSE);

        user.setFirstName("name");
        user.setLastName("surname");
        user.setLogin("kul");
        user.setContact("8798709");
        user.setId(0);

    }

    @Test
    public void testFindUserById() throws SQLException {
        UserDao userDao = new MySQLUserDao();
        userDao.findUserById( 3);

//        verify(mockConn, times(1)).prepareStatement(anyString());
//        verify(mockPreparedStmt, times(1)).setInt(anyInt(), anyInt());
//        verify(mockPreparedStmt, times(1)).executeQuery();
//        verify(mockResultSet, times(1)).next();
//        verify(mockResultSet, times(1)).close();
//        verify(mockPreparedStmt, times(1)).close();
    }

}




