package com.company.Model;

import com.company.Controller.MovieController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieModel {


    static SlopeOne so;
    MovieController movieController;
    Map<Integer, Map<Integer, Integer>> userRatingsData;

    Map<Integer, Integer> currentUserRatings;

    static ItemToItem itemToItem;


    public MovieModel() {


        userRatingsData = loadUserRatingData();
        so = new SlopeOne(userRatingsData);

        currentUserRatings = new HashMap<>();

        itemToItem = new ItemToItem(so.getMovieMap());



    }

    public void setMovieController(MovieController movieController) {
        this.movieController = movieController;
    }

    private static Map<Integer, Map<Integer, Integer>> loadUserRatingData() {

        Map<Integer, Map<Integer, Integer>> data = new HashMap<>();

        String csvFile = "src/data/ratings.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            Map<Integer, Integer> movieFrequency = new HashMap<>();

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] columns = line.split(cvsSplitBy);
                int rater_id = Integer.parseInt(columns[0]);
                int movie_id = Integer.parseInt(columns[1]);
                int rating = Integer.parseInt(columns[2]);
//                long time = Long.parseLong(columns[3]);


                if (data.containsKey(rater_id)) {
                    data.get(rater_id).put(movie_id, rating);
                } else {
                    Map<Integer, Integer> tempRating = new HashMap<>();
                    tempRating.put(movie_id, rating);
                    data.put(rater_id, tempRating);
                }

//                if (movieFrequency.containsKey(movie_id)) {
//                    movieFrequency.put(movie_id, movieFrequency.get(movie_id) + 1);
//                } else {
//                    movieFrequency.put(movie_id, 1);
//                }


                //System.out.println(columns[0] + " " + columns[1] + " " + columns[2] + " " + columns[3] + " ");

            }

//            movieFrequency = movieFrequency.entrySet().stream()
//                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                    .limit(10)
//                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//            System.out.println(movieFrequency);

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


    public Image getImageFromMovieCode(int movieCode) {

        Movie movieTemp = so.getMovieMap().get(movieCode);

        URL url = null;
        try {

            if (movieTemp.getPoster().contains("\"N/A\"")) {
                File imageFile = new File("/home/administrateur/IdeaProjects/MovieRecommendation/src/images/noImage.jpeg");
                return ImageIO.read(imageFile);

            }

            url = new URL(movieTemp.getPoster().replace("\"", ""));
            BufferedImage image = ImageIO.read(url);

            return image;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            File imageFile = new File("/home/administrateur/IdeaProjects/MovieRecommendation/src/images/noImage.jpeg");
            try {
                return ImageIO.read(imageFile);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return null;
    }

    public Movie getMovieFromId(int movieId) {
        return so.getMovieMap().get(movieId);

    }


    public void addUserRating(int movieId, int value) {

        currentUserRatings.put(movieId, value);

    }

    public Map<Integer, Integer> getCurrentMoviesRated() {
        return currentUserRatings;
    }

    public int getNextRecommendation() {


        Map<Integer, Double> prediction = so.predict(currentUserRatings);
//        Map<Integer, Double> prediction = so.weightlesspredict(currentUserRatings);

        prediction = prediction.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

//        System.out.println(prediction);

        for(int reponse:prediction.keySet()){

            if (!currentUserRatings.containsKey(reponse)){
                return reponse;
            }
        }

        return Integer.parseInt(null);
    }

    public int getNextNeighborgh() {

        for (int movie:currentUserRatings.keySet()){

            for (int movieNeighbourh: itemToItem.getMovieNeigbourhs(movie).keySet() ){
                if (!currentUserRatings.containsKey(movieNeighbourh)){
                    return movieNeighbourh;
                }
            }
        }

        return Integer.parseInt(null);

    }

    public void setUserRatingsToZero() {
        currentUserRatings.clear();
    }
}
