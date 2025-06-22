package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.MovieDto;
import com.example.miniNetflix.domain.entity.MovieEntity;
import com.example.miniNetflix.mappers.MovieMapperImpl;
import com.example.miniNetflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    private final MovieMapperImpl mapper;

    @Override
    public List<MovieDto> getAllMovie(String genre) {
        List<MovieEntity> movieEntities = (genre == null || genre.isBlank()) ?
                movieRepository.findAll(): movieRepository.findByGenresContainingIgnoreCase(genre);
        return movieEntities.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MovieDto getMovieById(Long id) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(id);

        return movieEntity.map(mapper::toDto).orElseThrow(()-> new RuntimeException("MovieNotFound"));
    }

    @Override
    public List<MovieDto> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title).stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
