package com.company.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


// https://www.programcreek.com/java-api-examples/index.php?source_dir=HappyResearch-master/happyresearch/src/main/java/happy/research/cf/SlopeOne.java


//https://dzone.com/articles/recommendation-engine-models

// Posibles DataSets to work on



public class SlopeOne {

    private Map<Integer, Map<Integer, Integer>> mData;
    private Map<Integer, Map<Integer, Double>> diffMatrix;
    private Map<Integer, Map<Integer, Integer>> freqMatrix;

    private static Map<Integer, Movie> movieMap;



    public SlopeOne(Map<Integer, Map<Integer, Integer>> mData) {
        this.mData = mData;
        buildDiffMatrix();
        this.movieMap = loadMovieData();



    }

    public static Map<Integer, Movie> getMovieMap() {
        return movieMap;
    }

    private static Map<Integer, Movie> loadMovieData() {

        Map<Integer, Movie> data = new HashMap<>();

        String csvFile = "src/data/ratedmoviesfull.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        int i = 0;
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

                Movie movieTemp = new Movie(title, year, country, genre, director, minutes, poster);

                data.put(movieId, movieTemp);

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

    public Map<Integer, Double> predict(int user)
    {

        Map<Integer, Integer> userRatings = mData.get(user);


        HashMap<Integer, Double> predictions = new HashMap<>();
        HashMap<Integer, Integer> frequencies = new HashMap<>();


        for (Integer j : diffMatrix.keySet())
        {
            frequencies.put(j, 0);
            predictions.put(j, 0.0);
        }
        for (Integer j : userRatings.keySet())
        {
            for (Integer k : diffMatrix.keySet())
            {
                try
                {
                    double newval = (diffMatrix.get(k).get(j) + userRatings.get(j)) * freqMatrix.get(k).get(j);
                    predictions.put(k, predictions.get(k) + newval);
                    frequencies.put(k, frequencies.get(k) + freqMatrix.get(k).get(j).intValue());
                } catch (NullPointerException e)
                {}
            }
        }
        HashMap<Integer, Double> cleanpredictions = new HashMap<>();
        for (Integer j : predictions.keySet())
        {
            if (frequencies.get(j) > 0)
            {
                cleanpredictions.put(j, predictions.get(j) / frequencies.get(j).intValue());
            }
        }
        for (int j : userRatings.keySet())
        {
            cleanpredictions.put(j, Double.valueOf(userRatings.get(j)));
        }
        return cleanpredictions;
    }

    public void printData() {
        for (int user : mData.keySet()) {
            System.out.println(user);
            print(mData.get(user));
        }


        for (Map.Entry<Integer, Movie> entryMovie : movieMap.entrySet()) {

//            System.out.print("\n" + entryMovie.getKey() + ":");
            System.out.format("\n %25s", entryMovie.getValue().getTitle() + ":" );
            printMatrixes(diffMatrix.get(entryMovie.getKey()), freqMatrix.get(entryMovie.getKey()));
        }
    }

    private void printMatrixes(Map<Integer,Double> ratings, Map<Integer,Integer> frequencies) {

//        System.out.println();
//        System.out.println(ratings);
//        System.out.println(frequencies);


            for (Map.Entry<Integer, Movie> entryMovie : movieMap.entrySet())
            {

                if (ratings != null && frequencies != null){
                    System.out.format("%10d", ratings.get(entryMovie.getKey()));
                    System.out.print(" ");
                    System.out.format("%10d", frequencies.get(entryMovie.getKey()));
                }
                else {
                    System.out.format("%10s", "null");
                    System.out.print(" ");
                    System.out.format("%10s", "null");


                }

            }
            System.out.println();





    }


    public void print(Map<Integer, Integer> user) {
        for (Integer j : user.keySet()) {

            System.out.println(" " + movieMap.get(j).getTitle() + " --> " + user.get(j).intValue());
        }
    }

    private void buildDiffMatrix() {
        diffMatrix = new HashMap<>();
        freqMatrix = new HashMap<>();
        // first iterate through users
        for (Map<Integer, Integer> user : mData.values()) {
            // then iterate through user data
            for (Map.Entry<Integer, Integer> entry : user.entrySet()) {
                int i1 = entry.getKey();
                int r1 = entry.getValue();

                if (!diffMatrix.containsKey(i1)) {
                    diffMatrix.put(i1, new HashMap<Integer, Double>());
                    freqMatrix.put(i1, new HashMap<Integer, Integer>());
                }

                for (Map.Entry<Integer, Integer> entry2 : user.entrySet()) {
                    int i2 = entry2.getKey();
                    int r2 = entry2.getValue();

                    int cnt = 0;
                    if (freqMatrix.get(i1).containsKey(i2)) {
                        cnt = freqMatrix.get(i1).get(i2);
                    }

                    Double diff = 0d;
                    if (diffMatrix.get(i1).containsKey(i2)) {
                        diff = diffMatrix.get(i1).get(i2);
                    }
                    int new_diff = r1 - r2;

                    freqMatrix.get(i1).put(i2, cnt + 1);
                    diffMatrix.get(i1).put(i2, diff + new_diff);
                }
            }
        }


        for (Integer j : diffMatrix.keySet()) {
            for (Integer i : diffMatrix.get(j).keySet()) {
                Double oldvalue = diffMatrix.get(j).get(i);
                int count = freqMatrix.get(j).get(i).intValue();
                diffMatrix.get(j).put(i, oldvalue / count);
            }
        }


    }


}
