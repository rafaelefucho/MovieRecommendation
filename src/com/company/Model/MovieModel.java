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


    public MovieModel() {

        userRatingsData = loadUserRatingData();
        so = new SlopeOne(userRatingsData);



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
            e.printStackTrace();
        }

        return null;
    }
}
