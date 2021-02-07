package com.finalProject.kuleshov.Cinema.model;

public class Ticket {
    private int id;
    private String filmName;
    private String fistNameUser;
    private String lastNameUser;
    private String dateSeance;
    private String timeSeance;
    private double priceSeance;
    private int numberSeat;
    private String img;
    private int freePlace;
    private int allPlace;
    private int duration;
    private String director;
    private String description;

    public Ticket() {
    }

    public Ticket(int id, String filmName, String fistNameUser, String lastNameUser, String dateSeance, String timeSeance, double priceSeance, int numberSeat) {
        this.id = id;
        this.filmName = filmName;
        this.fistNameUser = fistNameUser;
        this.lastNameUser = lastNameUser;
        this.dateSeance = dateSeance;
        this.timeSeance = timeSeance;
        this.priceSeance = priceSeance;
        this.numberSeat = numberSeat;
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

    public String getFistNameUser() {
        return fistNameUser;
    }

    public void setFistNameUser(String fistNameUser) {
        this.fistNameUser = fistNameUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
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

    public int getFreePlace() {
        return freePlace;
    }

    public void setFreePlace(int freePlace) {
        this.freePlace = freePlace;
    }

    public int getAllPlace() {
        return allPlace;
    }

    public void setAllPlace(int allPlace) {
        this.allPlace = allPlace;
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
        return "Ticket{" +
                "id=" + id +
                ", filmName='" + filmName + '\'' +
                ", fistNameUser='" + fistNameUser + '\'' +
                ", lastNameUser='" + lastNameUser + '\'' +
                ", dateSeance='" + dateSeance + '\'' +
                ", timeSeance='" + timeSeance + '\'' +
                ", priceSeance=" + priceSeance +
                ", numberSeat=" + numberSeat +
                ", img='" + img + '\'' +
                ", freePlace=" + freePlace +
                ", allPlace=" + allPlace +
                ", duration=" + duration +
                ", director='" + director + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
