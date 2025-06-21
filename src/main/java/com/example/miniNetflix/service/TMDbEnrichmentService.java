package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.TmdbMovieDto;
import com.example.miniNetflix.domain.entity.MovieEntity;
import com.example.miniNetflix.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TMDbEnrichmentService {

    private final MovieRepository movieRepository;
    private final TMDbClient tmDbClient;

    public TMDbEnrichmentService(MovieRepository movieRepository, TMDbClient tmDbClient) {
        this.movieRepository = movieRepository;
        this.tmDbClient = tmDbClient;
    }

    @Transactional
    public void enrichMoviesWithTMDb() {
        List<MovieEntity> movieEntityList = movieRepository.findAll();
        for(MovieEntity movie:movieEntityList){
            Optional<TmdbMovieDto> tmdbMovieDto = tmDbClient.searchMovieByTitle(movie.getTitle());
            tmdbMovieDto.ifPresent(data->{
                movie.setPosterUrl("https://image.tmdb.org/t/p/w500" + data.getPoster_path());
                movie.setOverview(data.getOverview());
                movie.setReleaseDate(LocalDate.parse(data.getRelease_date()));
                movie.setTmdbId(data.getId());
                movieRepository.save(movie);
                System.out.println("Enriched  "+movie.getTitle());
            });
        }
    }

}

