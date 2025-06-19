package com.example.miniNetflix.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TmdbMovieDto {
    private String poster_path;
    private String overview;
    private String release_date;
    private Long id;
    private String title;
    private Double vote_average;
}
