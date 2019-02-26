package com.company;

import com.company.Controller.MovieController;
import com.company.Model.Movie;
import com.company.Model.MovieModel;
import com.company.View.MovieView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


// I borrowed the data from here for educational purposes.
//https://github.com/RayhaanPirani/movie-recommendation-duke/tree/master/MovieRecommendationFramework/data

public class Main {

    static Set<String> setOfMovieCategorys = new HashSet<>();



    public static void main(String[] args) {


        MovieModel movieModel = new MovieModel();
        MovieView movieView = new MovieView();
        MovieController movieController = new MovieController();
        
        movieController.setMovieModel(movieModel);
        movieController.setMovieView(movieView);
        
        movieModel.setMovieController(movieController);
        movieView.setMovieController(movieController);
        
        movieController.init();
        
        
        //loadMovieData();



//        int user = 325;
//
//        Map<Integer,Double> prediction = so.predict(user);
//
//        so.print(usersWithRatings.get(user));
//
//        System.out.println();
//
//        prediction = prediction.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .limit(100)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//
//        Map<Integer, Movie> movieMap = so.getMovieMap();
//
//        for(int key : prediction.keySet()){
//
//            System.out.format("%45s --> %10.2f \n", movieMap.get(key).getTitle() ,prediction.get(key));
//
//        }


    }


    public static void loadMovieData() {

        String csvFile = "src/data/ratedmoviesfull.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        Set<String> setOfCategorys = new HashSet<>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] columns = line.split(cvsSplitBy);

                int movieId = Integer.parseInt(columns[0]);
                String title = columns[1];
                int year = Integer.parseInt(columns[2]);
                String country = columns[3];
                String genre = columns[4];
                String director = columns[5];
                int minutes = Integer.parseInt(columns[6]);
                String poster = columns[7];

                Movie movieTemp = new Movie(title, year, country, genre, director, minutes, poster, movieId);



                String[] genres = genre.replace("\"","").split(",");

                for (String asdf : genres){
                    setOfCategorys.add(asdf);
                }

            }
            setOfMovieCategorys = setOfCategorys;





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


   





}
