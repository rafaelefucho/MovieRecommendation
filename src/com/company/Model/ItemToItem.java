package com.company.Model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<Integer, Double> getMovieNeigbourhs(int movieId) {


        Map<Integer, Double> result = dotProductByPairMatrix.get(movieId).entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        result.remove(movieId);

        return result;

    }
}
