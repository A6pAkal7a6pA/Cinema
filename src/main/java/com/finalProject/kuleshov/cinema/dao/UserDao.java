package com.finalProject.kuleshov.cinema.dao;

import com.finalProject.kuleshov.cinema.entity.User;

import java.util.List;

public interface UserDao {
    /**
     * register user
     * @param user
     * @return
     */
    int registerUser(User user);

    /**
     * login user
     * @param login
     * @param password
     * @return
     */
    User logIn(String login, String password);

    /**
     * find all users
     * @return
     */
    List<User> findAllUsers();

    /**
     * select user by login
     * @param login
     * @return
     */
    User selectUserByLogin(String login);

    /**
     * checking the user for validity
     * @param user
     * @return
     */
    boolean checkUser(User user);

    /**
     * get user role by login password
     * @param login
     * @param password
     * @return
     */
    User.ROLE getRoleByLoginPassword(String login, String password);

    /**
     * find user by id
     * @param id
     * @return
     */
    User findUserById(int id);
}
