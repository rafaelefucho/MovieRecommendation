package com.company.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, Integer> movieVector;

    public Movie(String title, int year, String country, String genre, String director, int minutes, String poster, int movieId) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.director = director;
        this.minutes = minutes;
        this.poster = poster;
        this.movieId = movieId;

        this.movieVector = toDummyVariable();
    }

    public Movie() {

    }

    public Map<String, Integer> getMovieVector() {
        return movieVector;
    }

    public void setMovieVector(Map<String, Integer> movieVector) {
        this.movieVector = movieVector;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getMovieId() {
        return movieId;
    }

    public Map<String, Integer> toDummyVariable(){

        ArrayList<String> countrys = new ArrayList<>();
        countrys.addAll(Arrays.asList(new String[]{"Luxembourg", "Czech Republic", "New Zealand", "Yugoslavia", "Mexico", "Sweden", "Australia", "Bolivia", "Indonesia", "Greece", "Ireland", "Singapore", "Croatia", "Iran", "Morocco", "Iraq", "Chile", "Japan", "Uruguay", "Russia", "Algeria", "Argentina", "India", "Malaysia", "United Arab Emirates", "Ukraine", "Hungary", "Turkey", "West Germany", "India", "Canada", "Poland", "Turkey", "Belgium", "Georgia", "Finland", "Taiwan", "South Africa", "South Africa", "Georgia", "Peru", "Monaco", "Germany", "Belgium", "Switzerland", "Puerto Rico", "Fiji", "Hong Kong", "Bahamas", "Latvia", "USA", "Thailand", "Sweden", "Vietnam", "Poland", "Morocco", "Nigeria", "Bulgaria", "Palestine", "USA", "Israel", "China", "Qatar", "Dominican Republic", "Kenya", "Switzerland", "Bosnia and Herzegovina", "Spain", "Palestine", "Venezuela", "Czech Republic", "Brazil", "Israel", "Australia", "Lebanon", "Soviet Union", "Estonia", "Malta", "Egypt", "Netherlands", "Vietnam", "Norway", "Iceland", "Saudi Arabia", "South Korea", "Austria", "Cuba", "Yugoslavia", "UK", "Brazil", "Tunisia", "Ukraine", "Portugal", "Germany", "Ecuador", "Chile", "Belarus", "Japan", "Canada", "Mauritania", "Taiwan", "Bulgaria", "West Germany", "New Zealand", "Finland", "Kazakhstan", "Iran", "Argentina", "Italy", "UK", "Mali", "Italy", "Hong Kong", "Angola", "Jordan", "Iraq", "Thailand", "Laos", "N/A", "Haiti", "Singapore", "Slovakia", "Bhutan", "Egypt", "Iceland", "Greece", "South Korea", "Russia", "Peru", "Saudi Arabia", "Netherlands", "Pakistan", "China", "Ireland", "Austria", "France", "Romania", "Philippines", "Spain", "Romania", "Lithuania", "Serbia", "France", "Norway", "Denmark", "Dominican Republic", "Mexico", "Philippines", "Denmark", "Paraguay", "Liechtenstein", "Indonesia"}));

        ArrayList<String> genres = new ArrayList<>();
        genres.addAll(Arrays.asList(new String[]{"N/A","Film-Noir","Action","Fantasy","Musical","War","Reality-TV","History","Biography","History","Animation","Western","Adventure","Documentary","Mystery","Sport","Thriller","Biography","Comedy","Sci-Fi","Mystery","Western","Horror","Musical","Short","Family","Short","Thriller","Romance","Adventure","Horror","War","Drama","Romance","Music","Action","Comedy","Crime","Sci-Fi","Film-Noir","Drama","Music","Game-Show","Crime","Fantasy","Animation","Family"}));

        Map<String, Integer> movieVector = new HashMap<>();

        for(String countryTemp:countrys){

            if(country.contains(countryTemp)){
                movieVector.put(countryTemp,1);
            }
            else {
                movieVector.put(countryTemp,0);
            }
        }

        for(String genreTemp:genres){

            if(genre.contains(genreTemp)){
                movieVector.put(genreTemp,1);
            }
            else {
                movieVector.put(genreTemp,0);
            }
        }

        movieVector.put("year",year);
        movieVector.put("minutes",minutes);

        return movieVector;
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

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", country='" + country + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", minutes=" + minutes +
                ", poster='" + poster + '\'' +
                ", movieId=" + movieId +
                '}';
    }

    public double dotProduct(Movie movieB) {

        Map<String, Integer> movieVectorA = this.getMovieVector();
        Map<String, Integer> movieVectorB = movieB.getMovieVector();

        double result = 0;
        for (String keyTemp:movieVectorA.keySet()){
            result += movieVectorA.get(keyTemp) * movieVectorB.get(keyTemp);
        }

        return result;

    }
}
