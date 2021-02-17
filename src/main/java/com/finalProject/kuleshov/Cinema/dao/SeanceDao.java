package com.finalProject.kuleshov.Cinema.dao;

import com.finalProject.kuleshov.Cinema.entity.Seance;

import java.util.List;

public interface SeanceDao {

    public String selectCurrentDay();

    public List<Seance> selectScheduleByDay(String day);

    public Seance showSeanceById(int id);

    public boolean updateSeance(Seance seance);

    public Seance selectSeanceById(int id);

    public boolean deleteSeanceById(int id);

    public void addSeance(Seance seance);

    public List<Seance> showAllSeance();

    public List<Seance> showAllSeance(String sortRequest, int start, int total);

    public List<Seance> findAllSeanceByFilmId(int filmId);
}
