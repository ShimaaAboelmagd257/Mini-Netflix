package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.RatingDto;
import com.example.miniNetflix.domain.entity.Rating;
import com.example.miniNetflix.mappers.RatingsMapperImpl;
import com.example.miniNetflix.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;
    private final RatingsMapperImpl mapper;


    @Override
    public RatingDto saveRating(RatingDto ratingDto) {
        Rating rating = new Rating();
        rating.setId(new Rating.RatingId(ratingDto.getUserId(),ratingDto.getMovieId()));
        rating.setRating(ratingDto.getRating());
        return mapper.toDto(ratingRepository.save(rating));
    }

    @Override
    public List<RatingDto> getRatingsByUser(Long userId) {
        return ratingRepository.findByIdUserId(userId).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
