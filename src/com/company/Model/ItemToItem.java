package com.company.Model;

import java.util.HashMap;
import java.util.Map;

public class ItemToItem {

    private Map<Integer, Map<Integer, Double>> dotProductByPairMatrix;


    public ItemToItem(Map<Integer,Movie> movieMap) {

        dotProductByPairMatrix = new HashMap<>();
        buildDiffMatrix(movieMap);

    }



    private void buildDiffMatrix(Map<Integer,Movie> movieMap) {

        for (int movieA:movieMap.keySet()){

            Map<Integer, Double> diffMapByMovie = new HashMap<>();

            for (int movieB:movieMap.keySet()){

                double resultDotProduct = movieMap.get(movieA).dotProduct(movieMap.get(movieB));
                diffMapByMovie.put(movieB,resultDotProduct);
            }

            dotProductByPairMatrix.put(movieA,diffMapByMovie);
        }
    }

    public void getMovieNeigbourhs(int i) {





    }
}
