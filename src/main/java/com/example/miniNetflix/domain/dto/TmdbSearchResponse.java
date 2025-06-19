package com.example.miniNetflix.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class TmdbSearchResponse {
    private List<TmdbMovieDto> results;
}
