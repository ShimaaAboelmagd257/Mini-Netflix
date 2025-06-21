package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.RatingDto;

import java.util.List;

public interface RatingService {
    RatingDto saveRating(RatingDto ratingDto);

    List<RatingDto> getRatingsByUser(Long userId);
}
