package com.finalProject.kuleshov.Cinema.constants;

public class SQLConstants {
    public static final String INSERT_USER =
            "INSERT INTO users (id, firstName, lastName, login, password, contact, email) VALUES (?, ?, ?, ?, ?, ?, ?);";
    public static final String SELECT_ALL_USERS_BY_LOGIN_PASSWORD =
            "SELECT * FROM users WHERE login=? AND password=?";
    public static final String SELECT_ALL_USER = "SELECT * FROM users order by reg_date_user desc";
    public static final String SELECT_USER_BY_LOGIN =
            "SELECT id, firstName, lastName, contact, email, reg_date_user FROM users WHERE login=?";
    public static final String SELECT_ROLE_BY_LOGIN_PASSWORD =
            "SELECT role FROM users WHERE login=? AND password=?";
    public static final String INSERT_FILM =
            "INSERT INTO films (name, directedBy, description, duration, picture) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_FILMS = "select * from films order by reg_date desc;";
    public static final String SELECT_FILM_BY_ID = "SELECT * FROM films WHERE id=?;";
    public static final String DELETE_FILM_BY_ID = "DELETE FROM films WHERE id=?;";
    public static final String DELETE_SEANCE_BY_ID = "DELETE FROM seance WHERE id=?;";
    public static final String UPDATE_FILM_BY_ID =
            "UPDATE films set name=?, directedBy=?, description=?, duration=?, picture=?  WHERE id=?";
    public static final String SELECT_ALL_SEANCES =
            "select s.id                                                as id,\n" +
                    "       f.picture                                           as photo_film,\n" +
                    "       f.name                                              as film_name,\n" +
                    "       s.date_seance                                       as date_seance,\n" +
                    "       date_format(s.time_seance, '%k:%i') as time_seance,\n" +
                    "       s.price_seance                                      as price,\n" +
                    "       f.duration                                          as duration,\n" +
                    "       f.directedBy                                        as director,\n" +
                    "       f.description                                       as description,\n" +
                    "       s.film_id                                           as film_id,\n" +
                    "       s.number_of_seats                                   as number_of_seats,\n" +
                    "       s.number_of_seats - (count(distinct t.number_seat)) as free_places\n" +
                    "\n" +
                    "from seance s\n" +
                    "         join films f\n" +
                    "              on f.id = s.film_id\n" +
                    "         left join tickets t on s.id = t.seance_id\n" +
                    "where s.date_seance >= curdate()\n" +
                    "group by s.id ";

    public static final String SELECT_ALL_SEANCES_ADM =
            "select s.id                                                as id,\n" +
                    "       f.picture                                           as photo_film,\n" +
                    "       f.name                                              as film_name,\n" +
                    "       s.date_seance                                       as date_seance,\n" +
                    "       date_format(s.time_seance, '%k:%i') as time_seance,\n" +
                    "       s.price_seance                                      as price,\n" +
                    "       f.duration                                          as duration,\n" +
                    "       f.directedBy                                        as director,\n" +
                    "       f.description                                       as description,\n" +
                    "       s.film_id                                           as film_id,\n" +
                    "       s.number_of_seats                                   as number_of_seats,\n" +
                    "       s.number_of_seats - (count(distinct t.number_seat)) as free_places\n" +
                    "\n" +
                    "from seance s\n" +
                    "         join films f\n" +
                    "              on f.id = s.film_id\n" +
                    "         left join tickets t on s.id = t.seance_id\n" +
                    "group by s.id " +
                    " order by s.date_seance desc, s.time_seance desc";
    public static final String SELECT_FILM_ID_NAME = "SELECT id, name FROM films";
    public static final String INSERT_SEANCE = "insert into seance (film_id, date_seance, time_seance, price_seance) VALUES (?, ?, ?, ?);";
    public static final String SELECT_SEANCE_BY_ID =
            "Select s.id, f.name as film_name, s.date_seance, s.time_seance as time_s, s.price_seance as price, s.number_of_seats, s.film_id\n" +
                    "From seance s\n" +
                    "join films f ON s.film_id = f.id\n" +
                    "WHERE s.id = ?;";
    public static final String UPDATE_SEANCE_BY_ID =
            "UPDATE seance set id=?, date_seance=?, time_seance=?, price_seance=?, film_id=? WHERE id=?";
    public static final String INSERT_TICKET = "INSERT INTO tickets(user_id, seance_id, number_seat) VALUES (?, ?, ?);";
    public static final String SELECT_OCCUPIED_PLACES = "SELECT number_seat FROM tickets WHERE seance_id=?";

    public static final String SELECT_FREE_PLACES = "select s.number_of_seats - count(t.number_seat) as free_places\n" +
            "from tickets t\n" +
            "         join seance s on s.id = t.seance_id\n" +
            "where s.id = ?";

    public static final String SELECT_ALL_TICKETS_USER = "select t.id           as id,\n" +
            "       f.name         as movie_title,\n" +
            "       s.date_seance  as date_seance,\n" +
            "       s.time_seance  as time_seance,\n" +
            "       t.number_seat  as place,\n" +
            "       s.price_seance as price\n" +
            "from tickets t\n" +
            "         join seance s on t.seance_id = s.id\n" +
            "         join films f on s.film_id = f.id\n" +
            "where user_id = ?" +
            "  and s.date_seance >= current_date\n" +
            "order by s.date_seance asc, s.time_seance asc;\n";

    public static final String SELECT_SEANCE_BY_ID_JOIN = "select s.id                                                as id,\n" +
            "       f.picture                                           as photo_film,\n" +
            "       f.name                                              as film_name,\n" +
            "       s.date_seance                                       as date_seance,\n" +
            "       date_format(s.time_seance, '%k:%i') as time_seance,\n" +
            "       s.price_seance                                      as price,\n" +
            "       f.duration                                          as duration,\n" +
            "       f.directedBy                                        as director,\n" +
            "       f.description                                       as description,\n" +
            "       s.film_id                                           as film_id,\n" +
            "       s.number_of_seats                                   as number_of_seats,\n" +
            "       s.number_of_seats - (count(distinct t.number_seat)) as free_places\n" +
            "\n" +
            "from seance s\n" +
            "         join films f\n" +
            "              on f.id = s.film_id\n" +
            "         left join tickets t on s.id = t.seance_id\n" +
            "where s.id = ?";

    public static final String SELECT_SEANCES_BY_DAY = "SELECT distinct s.id                                as id,\n" +
            "                f.name                              as film_name,\n" +
            "                s.film_id                           as film_id,\n" +
            "                dayname(s.date_seance)              as day_name,\n" +
            "                s.date_seance                       as date_seance,\n" +
            "                date_format(s.time_seance, '%k:%i') as time_seance,\n" +
            "                s.time_seance                       as time_s\n" +
            "FROM seance s\n" +
            "         join films f on f.id = s.film_id\n" +
            "WHERE YEARWEEK(s.date_seance) = YEARWEEK(NOW())\n" +
            "  and dayname(s.date_seance) = ? " +
            "order by time_s asc;";

    public static final String SELECT_CURRENT_DAY = "select dayname(now()) as day_name";

    public static final String SELECT_ALL_SEANCES_BY_FILM_ID = "SELECT distinct s.id                                as id,\n" +
            "                f.name                              as film_name,\n" +
            "                s.film_id                           as film_id,\n" +
            "                dayname(s.date_seance)              as day_name,\n" +
            "                s.date_seance                       as date_seance,\n" +
            "                date_format(s.time_seance, '%k:%i') as time_seance,\n" +
            "                s.time_seance                       as time_s\n" +
            "FROM seance s\n" +
            "         join films f on f.id = s.film_id\n" +
            "WHERE YEARWEEK(s.date_seance) = YEARWEEK(NOW())\n" +
            "  and s.film_id = ? " +
            "order by 5 asc, 7 asc;";
}