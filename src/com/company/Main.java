package com.company;

import com.company.Model.Movie;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {



        Map<Integer, Map<Integer, Integer>> usersWithRatings = loadUserRatingData();
        Map<Integer, Movie> movieMap = loadMovieData();


        



    }

    private static Map<Integer,Movie> loadMovieData() {

        Map<Integer, Movie> data = new HashMap<>();

        String csvFile = "src/data/ratedmoviesfull.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        int i=0;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                i++;
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

                Movie movieTemp = new Movie(title,year,country,genre,director,minutes,poster);

                data.put(movieId,movieTemp);

            }

            return data;

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

        return data;

    }

    private static Map<Integer, Map<Integer, Integer>> loadUserRatingData() {

        Map<Integer, Map<Integer, Integer>> data = new HashMap<>();

        String csvFile = "src/data/ratings.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] columns = line.split(cvsSplitBy);
                int rater_id = Integer.parseInt(columns[0]);
                int movie_id = Integer.parseInt(columns[1]);
                int rating = Integer.parseInt(columns[2]);
                long time = Long.parseLong(columns[3]);


                if (data.containsKey(rater_id)){
                    data.get(rater_id).put(movie_id,rating);
                }
                else {
                    Map<Integer, Integer> tempRating = new HashMap<>();
                    tempRating.put(movie_id,rating);
                    data.put(rater_id,tempRating);
                }



        //System.out.println(columns[0] + " " + columns[1] + " " + columns[2] + " " + columns[3] + " ");

            }

            return data;

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

        return data;

    }





}
