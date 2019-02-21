package com.company.Model;

public class Movie {

    //id,title,year,country,genre,director,minutes,poster

    private String title;
    private int year;
    private String country;
    private String genre;
    private String director;
    private int minutes;
    private String poster;

    public Movie(String title, int year, String country, String genre, String director, int minutes, String poster) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.director = director;
        this.minutes = minutes;
        this.poster = poster;
    }
}
