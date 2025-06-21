package com.example.miniNetflix.util;

import com.example.miniNetflix.domain.entity.Rating;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SimilarityMatrixBuilder {
    public Map<Long,Map<Long,Double>> computeMovieSimilarity(List<Rating> ratings){


        Map<Long,Map<Long,Double>> movieUserRatings = new HashMap<>();
        for (Rating rating:ratings){
            Long movieId = rating.getId().getMovieId();
            Long userId = rating.getId().getUserId();
            movieUserRatings.computeIfAbsent(movieId, k->new HashMap<>()).put(userId,rating.getRating());
        }


        Map<Long,Map<Long,Double>> similarityMatrix = new HashMap<>();
        for (Long movie1: movieUserRatings.keySet()){
            for(Long movie2 : movieUserRatings.keySet()){
                if(!movie1.equals(movie2)){
                    double similarity = cosineSimilarity(movieUserRatings.get(movie1),movieUserRatings.get(movie2));
                    similarityMatrix.computeIfAbsent(movie1,k-> new HashMap<>()).put(movie2,similarity);
                }
            }
        }
        return similarityMatrix;

    }

    private double cosineSimilarity(Map<Long, Double> vectorA, Map<Long, Double> vectorB) {
        Set<Long> common = new HashSet<>(vectorA.keySet());
        common.retainAll(vectorB.keySet());
        if(common.isEmpty()) return 0.0;
        double dot = 0.0, normal1 = 0.0 , normal2 = 0.0;
        for(Long key : common){
            dot+= vectorA.get(key) * vectorB.get(key);
        }
        for (double v: vectorA.values()){
            normal1 += v*v;
        }
        for (double v: vectorB.values()){
            normal2 += v*v;
        }
        return dot / (Math.sqrt(normal1)*Math.sqrt(normal2));

    }
}
