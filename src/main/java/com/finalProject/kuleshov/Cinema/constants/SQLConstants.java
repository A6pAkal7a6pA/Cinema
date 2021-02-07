package com.finalProject.kuleshov.Cinema.constants;

public class SQLConstants {
    public static final String INSERT_USER =
            "INSERT INTO users (id, firstName, lastName, login, password, contact, email) VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String SELECT_ALL_USERS_BY_LOGIN_PASSWORD =
            "SELECT * FROM users WHERE login=? AND password=?";
    public static final String SELECT_ALL_USER = "SELECT * FROM users";
    public static final String SELECT_USER_BY_LOGIN =
            "SELECT id, firstName, lastName, contact, email, reg_date_user FROM users WHERE login=?";
    public static final String SELECT_ROLE_BY_LOGIN_PASSWORD =
            "SELECT role FROM users WHERE login=? AND password=?";
    public static final String INSERT_FILM =
            "INSERT INTO films (name, directedBy, description, duration, picture) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_FILMS = "SELECT * FROM films";
    public static final String SELECT_FILM_BY_ID = "SELECT * FROM films WHERE id=?;";
    public static final String DELETE_FILM_BY_ID = "DELETE FROM films WHERE id=?;";
    public static final String DELETE_SEANCE_BY_ID = "DELETE FROM seance WHERE id=?;";
    public static final String UPDATE_FILM_BY_ID =
            "UPDATE films set name=?, directedBy=?, description=?, duration=? WHERE id=?";
    public static final String SELECT_ALL_SEANCES =
            "select s.id                                     as id,\n" +
                    "       f.picture                                as photo_film,\n" +
                    "       f.name                                   as film_name,\n" +
                    "       s.date_seance                            as date_seance,\n" +
                    "       s.time_seance                            as time_seance,\n" +
                    "       s.price_seance                           as price,\n" +
                    "       f.duration                               as duration,\n" +
                    "       f.directedBy                             as director,\n" +
                    "       f.description                            as description,\n" +
                    "       s.film_id                                as film_id,\n" +
                    "       s.number_of_seats                        as number_of_seats\n" +
                    "       \n" +
                    "from seance s\n" +
                    "         join films f on f.id = s.film_id;";
    public static final String SELECT_FILM_ID_NAME = "SELECT id, name FROM films";
    public static final String INSERT_SEANCE = "insert into seance (film_id, date_seance, time_seance, price_seance) VALUES (?, ?, ?, ?);";
    public static final String SELECT_SEANCE_BY_ID =
            "Select s.id, f.name as film_name, s.date_seance, s.time_seance as time_s, s.price_seance as price, s.number_of_seats, s.film_id\n" +
                    "From seance s\n" +
                    "join films f ON s.film_id = f.id\n" +
                    "WHERE s.id = ?;";
    public static final String UPDATE_SEANCE_BY_ID =
            "UPDATE seance set id=?, date_seance=?, time_seance=?, price_seance=?, film_id=? WHERE id=?";
    public static final String SELECT_ALL_SEATS = "SELECT number_seat FROM tickets;";
    public static final String INSERT_TICKET = "INSERT INTO tickets(user_id, seance_id, number_seat) VALUES (?, ?, ?);";
    public static final String SELECT_OCCUPIED_PLACES = "SELECT number_seat FROM tickets WHERE seance_id=?";
    public static final String SELECT_NEED_SEANCE_BY_ID = "select f.picture as photo_film,\n" +
            "       f.name as film_name,\n" +
            "       s.date_seance as date_seance,\n" +
            "       s.time_seance as time_seance,\n" +
            "       s.price_seance as price,\n" +
            "       s.number_of_seats - count(t.number_seat) as free_place,\n" +
            "       s.number_of_seats as all_place,\n" +
            "       f.duration as duration,\n" +
            "       f.directedBy as director,\n" +
            "       f.description as description\n" +
            "from tickets t\n" +
            "    join seance s on s.id = t.seance_id\n" +
            "    join films f on f.id = s.film_id\n" +
            "    where seance_id = ?;";

    public static final String SELECT_FREE_PLACES = "select s.number_of_seats - count(t.number_seat) as free_places\n" +
            "from tickets t\n" +
            "         join seance s on s.id = t.seance_id\n" +
            "where s.id = ?";
}