package com.company.Controller;

import com.company.Model.Movie;
import com.company.Model.MovieModel;
import com.company.View.MovieView;

import java.awt.*;
import java.util.Map;

public class MovieController {

    MovieModel movieModel;
    MovieView movieView;

    public void setMovieView(MovieView movieView) {
        this.movieView = movieView;
    }

    public void setMovieModel(MovieModel movieModel) {
        this.movieModel = movieModel;
    }

    public void init() {
        movieView.initWindow();

    }

    public Image getImageFromMovieCode(int movieCode) {

        return movieModel.getImageFromMovieCode(movieCode);

    }

    public Movie getMovieFromId(int movieId) {
        return movieModel.getMovieFromId(movieId);
    }


    public void addUserRating(int movieId, int value) {
        movieModel.addUserRating(movieId, value);
    }

    public Map<Integer,Integer> getCurrentMoviesRated() {
        return movieModel.getCurrentMoviesRated();
    }

    public int nextRecomendation() {
        return movieModel.getNextRecommendation();
    }

    public int nextNeighbourh() {
        return movieModel.getNextNeighborgh();
    }

    public void setUserRatingsToZero() {
        movieModel.setUserRatingsToZero();

    }
}
