package com.example.demo.models;

public class Pelicula {
    public int id;
    public String film;
    public String genre;
    public String studio;
    public int score;
    public int year;

    public Pelicula(int id, String film, String genre, String studio, int score, int year) {
        this.id = id;
        this.film = film;
        this.genre = genre;
        this.studio = studio;
        this.score = score;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
