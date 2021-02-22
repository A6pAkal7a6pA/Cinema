package com.finalProject.kuleshov.cinema.dao;

import com.finalProject.kuleshov.cinema.entity.Seance;

import java.util.Collection;
import java.util.List;

public interface SeanceDao {

    /**
     * select current day
     * @return
     */
    String selectCurrentDay();

    /**
     * select all seances by day
     * @param day
     * @return
     */
    List<Seance> selectScheduleByDay(String day);

    /**
     * find seance by id
     * @param id
     * @return
     */
    Seance showSeanceById(int id);

    /**
     * update seance
     * @param seance
     * @return
     */
    boolean updateSeance(Seance seance);

    /**
     * select seance by id
     * @param id
     * @return
     */
    Seance selectSeanceById(int id);

    /**
     * delete seance by id
     * @param id
     * @return
     */
    boolean deleteSeanceById(int id);

    /**
     * add seance
     * @param seance
     */
    void addSeance(Seance seance);

    /**
     * find all seances for admin
     * @return
     */
    List<Seance> showAllSeanceForAMD();

    /**
     * find all seances for pagination and sorting
     * @param sortRequest
     * @param start
     * @param total
     * @return
     */
    List<Seance> showAllSeance(String sortRequest, int start, int total);

    /**
     * find all seances for User
     * @return
     */
    List<Seance> showAllSeance();

    /**
     * find all seances by filmId
     * @param filmId
     * @return
     */
    List<Seance> findAllSeanceByFilmId(int filmId);

    /**
     * find all seances for covers-slider on main page
     * @return
     */
    List<Seance> showAllSeanceForCovers();
}
