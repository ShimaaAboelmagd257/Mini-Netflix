package com.example.miniNetflix.domain.dto;

import com.example.miniNetflix.domain.entity.Rating;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingDto {
    private long userId;
    private long movieId;
    private double rating;
}
