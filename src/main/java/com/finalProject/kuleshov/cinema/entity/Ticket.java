package com.finalProject.kuleshov.cinema.entity;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {

    private static final long serialVersionUID = -507413582644423308L;

    private int id;
    private String filmName;
    private String dateSeance;
    private String timeSeance;
    private double priceSeance;
    private int numberSeat;
    private String img;
    private int duration;
    private String director;
    private String description;
    private int userId;
    private int seanceId;
    private int purchasedPlaces;
    private int sumPrice;
    private int allOccupiedPlaces;
    private int firstHalfDay;
    private int secondHalfDay;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getDateSeance() {
        return dateSeance;
    }

    public void setDateSeance(String dateSeance) {
        this.dateSeance = dateSeance;
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

    public int getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(int numberSeat) {
        this.numberSeat = numberSeat;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(int seanceId) {
        this.seanceId = seanceId;
    }

    public int getPurchasedPlaces() {
        return purchasedPlaces;
    }

    public void setPurchasedPlaces(int purchasedPlaces) {
        this.purchasedPlaces = purchasedPlaces;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(int sumPrice) {
        this.sumPrice = sumPrice;
    }

    public int getAllOccupiedPlaces() {
        return allOccupiedPlaces;
    }

    public void setAllOccupiedPlaces(int allOccupiedPlaces) {
        this.allOccupiedPlaces = allOccupiedPlaces;
    }

    public int getFirstHalfDay() {
        return firstHalfDay;
    }

    public void setFirstHalfDay(int firstHalfDay) {
        this.firstHalfDay = firstHalfDay;
    }

    public int getSecondHalfDay() {
        return secondHalfDay;
    }

    public void setSecondHalfDay(int secondHalfDay) {
        this.secondHalfDay = secondHalfDay;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", filmName='" + filmName + '\'' +
                ", dateSeance='" + dateSeance + '\'' +
                ", timeSeance='" + timeSeance + '\'' +
                ", priceSeance=" + priceSeance +
                ", numberSeat=" + numberSeat +
                ", img='" + img + '\'' +
                ", duration=" + duration +
                ", director='" + director + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", seanceId=" + seanceId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.priceSeance, priceSeance) == 0 && numberSeat == ticket.numberSeat && Objects.equals(timeSeance, ticket.timeSeance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeSeance, priceSeance, numberSeat);
    }
}
