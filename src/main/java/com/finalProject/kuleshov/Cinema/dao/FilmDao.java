package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.entity.Film;

import java.util.List;

public interface FilmDao {

    public List<Film> selectFilmIdName();

    public void addFilm(Film film);

    public boolean updateFilm(Film film);

    public Film selectFilmById(int id);

    public boolean deleteFilmById(int id);

    public List<Film> findAllFilms();
}
