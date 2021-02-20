package com.finalProject.kuleshov.cinema.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = 2689889919052531628L;

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String contact;
    private ROLE role;
    private String email;
    private Date date;

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public ROLE getRole() {
        return role;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(login, user.login) && Objects.equals(contact, user.contact) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, login, contact, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", contact='" + contact + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", date=" + date +
                '}';
    }
}
