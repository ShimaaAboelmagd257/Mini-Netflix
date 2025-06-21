package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.MovieDto;

import java.util.List;

public class MovieServiceImpl implements MovieService{
    @Override
    public List<MovieDto> getAllMovie(String genre) {
        return List.of();
    }

    @Override
    public MovieDto getMovieById(Long id) {
        return null;
    }

    @Override
    public List<MovieDto> searchMoviesByTitle(String title) {
        return List.of();
    }
}
