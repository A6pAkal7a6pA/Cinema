package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.entity.User;

import java.util.List;

public interface UserDao {

    public int registerUser(User user);

    public User logIn(String login, String password);

    public List<User> findAllUsers();

    public User selectUserByLogin(String login);

    public boolean checkUser(User user);

    public User checkAccess(String login, String password);

    public User.ROLE getRoleByLoginPassword(String login, String password);
}
