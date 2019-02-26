package com.company.Model;

import java.util.ArrayList;
import java.util.Arrays;

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

    public void toDummyVariable(){

        ArrayList<String> countrys = new ArrayList<>();
        countrys.addAll(Arrays.asList(new String[]{"Luxembourg", "Czech Republic", "New Zealand", "Yugoslavia", "Mexico", "Sweden", "Australia", "Bolivia", "Indonesia", "Greece", "Ireland", "Singapore", "Croatia", "Iran", "Morocco", "Iraq", "Chile", "Japan", "Uruguay", "Russia", "Algeria", "Argentina", "India", "Malaysia", "United Arab Emirates", "Ukraine", "Hungary", "Turkey", "West Germany", "India", "Canada", "Poland", "Turkey", "Belgium", "Georgia", "Finland", "Taiwan", "South Africa", "South Africa", "Georgia", "Peru", "Monaco", "Germany", "Belgium", "Switzerland", "Puerto Rico", "Fiji", "Hong Kong", "Bahamas", "Latvia", "USA", "Thailand", "Sweden", "Vietnam", "Poland", "Morocco", "Nigeria", "Bulgaria", "Palestine", "USA", "Israel", "China", "Qatar", "Dominican Republic", "Kenya", "Switzerland", "Bosnia and Herzegovina", "Spain", "Palestine", "Venezuela", "Czech Republic", "Brazil", "Israel", "Australia", "Lebanon", "Soviet Union", "Estonia", "Malta", "Egypt", "Netherlands", "Vietnam", "Norway", "Iceland", "Saudi Arabia", "South Korea", "Austria", "Cuba", "Yugoslavia", "UK", "Brazil", "Tunisia", "Ukraine", "Portugal", "Germany", "Ecuador", "Chile", "Belarus", "Japan", "Canada", "Mauritania", "Taiwan", "Bulgaria", "West Germany", "New Zealand", "Finland", "Kazakhstan", "Iran", "Argentina", "Italy", "UK", "Mali", "Italy", "Hong Kong", "Angola", "Jordan", "Iraq", "Thailand", "Laos", "N/A", "Haiti", "Singapore", "Slovakia", "Bhutan", "Egypt", "Iceland", "Greece", "South Korea", "Russia", "Peru", "Saudi Arabia", "Netherlands", "Pakistan", "China", "Ireland", "Austria", "France", "Romania", "Philippines", "Spain", "Romania", "Lithuania", "Serbia", "France", "Norway", "Denmark", "Dominican Republic", "Mexico", "Philippines", "Denmark", "Paraguay", "Liechtenstein", "Indonesia"}));

        ArrayList<String> genres = new ArrayList<>();
        genres.addAll(Arrays.asList(new String[]{"N/A","Film-Noir","Action","Fantasy","Musical","War","Reality-TV","History","Biography","History","Animation","Western","Adventure","Documentary","Mystery","Sport","Thriller","Biography","Comedy","Sci-Fi","Mystery","Western","Horror","Musical","Short","Family","Short","Thriller","Romance","Adventure","Horror","War","Drama","Romance","Music","Action","Comedy","Crime","Sci-Fi","Film-Noir","Drama","Music","Game-Show","Crime","Fantasy","Animation","Family"}));

        //TODO Tengo que construir un hashMap con los valores numericos del vector, crear otra para hacer el producto punto y despues una matriz de distancias por pares
        


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
