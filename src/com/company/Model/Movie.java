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
    private int movieId;

    public Movie(String title, int year, String country, String genre, String director, int minutes, String poster, int movieId) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.director = director;
        this.minutes = minutes;
        this.poster = poster;
        this.movieId = movieId;
    }

    public Movie() {

    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
