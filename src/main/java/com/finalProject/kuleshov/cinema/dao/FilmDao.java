package com.finalProject.kuleshov.cinema.dao;

import com.finalProject.kuleshov.cinema.entity.Film;

import java.util.List;

public interface FilmDao {
    /**
     * select film id and film name
     * @return
     */
    List<Film> selectFilmIdName();

    /**
     * add film
     * @param film
     */
    void addFilm(Film film);

    /**
     * update film
     * @param film
     * @return
     */
    boolean updateFilm(Film film);

    /**
     * select film by id
     * @param id
     * @return
     */
    Film selectFilmById(int id);

    /**
     * delete film by id
     * @param id
     * @return
     */
    boolean deleteFilmById(int id);

    /**
     * find all film
     * @return
     */
    List<Film> findAllFilms();
}
