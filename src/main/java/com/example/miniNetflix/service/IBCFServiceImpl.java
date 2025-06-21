package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.MovieDto;
import com.example.miniNetflix.domain.entity.MovieEntity;
import com.example.miniNetflix.domain.entity.Rating;
import com.example.miniNetflix.mappers.MovieMapperImpl;
import com.example.miniNetflix.repository.MovieRepository;
import com.example.miniNetflix.repository.RatingRepository;
import com.example.miniNetflix.util.SimilarityMatrixBuilder;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IBCFServiceImpl implements IBCFService{
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final MovieMapperImpl mapper;
    private final SimilarityMatrixBuilder similarityMatrixBuilder;
    private static final Logger log = LoggerFactory.getLogger(IBCFServiceImpl.class);
    @Override
    public List<MovieDto> recommendationsForUser(Long userId, int limit, String genre) {

        //step 1 Fetch all movieRatings
        List<Rating> ratings = ratingRepository.findAll();
        log.info("IBCFServiceImpl  Total ratings loaded: {}" , ratings.size());

        //step 2 Send the all ratings to be computation
        Map<Long , Map<Long,Double>> similarityMatrix =
                similarityMatrixBuilder.computeMovieSimilarity(ratings);
        log.info("IBCFServiceImpl  Similarity matrix size: {}", similarityMatrix.size());

       //step 3 Get user watched movies
        List<Rating> userRatings = ratingRepository.findByIdUserId(userId);
        Set<Long> watched = userRatings.stream().map(r-> r.getId().getMovieId()).collect(Collectors.toSet());
        log.info("IBCFServiceImpl User {} has rated {} movies  ",  userId , watched.size());

        //step 4 Get Suggested  movies score
        Map<Long,Double> suggestedMovieScores = new HashMap<>();
        for(Rating rating:userRatings){
            Long ratedMovieId = rating.getId().getMovieId();
            double userRating = rating.getRating();
            Map<Long,Double> similarMovies =
                    similarityMatrix.getOrDefault(ratedMovieId,new HashMap<>());
            for (Map.Entry<Long,Double> entry : similarMovies.entrySet()){
                Long candidateId = entry.getKey();
                double sim = entry.getValue();
                if(!watched.contains(candidateId)){
                    suggestedMovieScores.merge(candidateId,sim*userRating,Double::sum);
                }
            }
        }
        log.info("IBCFServiceImpl Suggested movies scored: {}",  suggestedMovieScores.size());

        // step 5 Filter Suggested movies scores by type
        Set<Long> allowedGenres = movieRepository.findByGenresContainingIgnoreCase(genre).
                stream()
                .map(MovieEntity::getMovieId).collect(Collectors.toSet());
        suggestedMovieScores.entrySet().stream()
                .sorted(Map.Entry.<Long, Double>comparingByValue().reversed())
                .limit(5)
                .forEach(e -> log.info("IBCFServiceImpl  Candidate movieId={ }, score={ } ", e.getKey() ,   e.getValue()));

        // step 6 sort
        return suggestedMovieScores.entrySet().stream().filter(entry -> allowedGenres.contains(entry.getKey())).sorted(Map.Entry.<Long,Double>comparingByValue().reversed())
                .limit(limit)
                .map(entry -> movieRepository
                        .findById(entry.getKey()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(mapper::mapTo)
                .collect(Collectors.toList());
    }
}
