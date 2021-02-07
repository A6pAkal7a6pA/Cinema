package com.finalProject.kuleshov.Cinema.model;

import java.time.LocalTime;
import java.util.Date;

public class Seance {
    private int id;
    private String date;
    private int numberOfSeats;
    private int filmId;
    private String filmName;
    private String timeSeance;
    private double priceSeance;
    private String img;
    private int duration;
    private String director;
    private String description;


    public Seance() {
    }

    public Seance(String date, int filmId, String timeSeance, double priceSeance) {
        this.date = date;
        this.filmId = filmId;
        this.timeSeance = timeSeance;
        this.priceSeance = priceSeance;
    }

    public Seance(String date, int filmId, String filmName, String timeSeance, double priceSeance) {
        this.date = date;
        this.filmId = filmId;
        this.filmName = filmName;
        this.timeSeance = timeSeance;
        this.priceSeance = priceSeance;
    }

    public Seance(int id, int filmId, String date, String timeSeance, double priceSeance) {
        this.id = id;
        this.date = date;
        this.filmId = filmId;
        this.timeSeance = timeSeance;
        this.priceSeance = priceSeance;
    }

    public Seance(int id, int filmId, String date, String timeSeance, double priceSeance, String filmName) {
        this.id = id;
        this.date = date;
        this.filmId = filmId;
        this.timeSeance = timeSeance;
        this.priceSeance = priceSeance;
        this.filmName = filmName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getTimeSeance() {
        return timeSeance;
    }

    public void setTimeSeance(String timeSeance) {
        this.timeSeance = timeSeance;
    }

    public double getPriceSeance() {
        return priceSeance;
    }

    public void setPriceSeance(double priceSeance) {
        this.priceSeance = priceSeance;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", filmId=" + filmId +
                ", filmName='" + filmName + '\'' +
                ", timeSeance='" + timeSeance + '\'' +
                ", priceSeance=" + priceSeance +
                '}';
    }
}