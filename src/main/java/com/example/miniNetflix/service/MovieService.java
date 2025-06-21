package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.MovieDto;

import java.util.List;

public interface MovieService {
    List<MovieDto> getAllMovie(String genre);

    MovieDto getMovieById(Long id);

    List<MovieDto> searchMoviesByTitle(String title);
}
