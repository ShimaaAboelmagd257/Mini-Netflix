package com.example.miniNetflix.controller.impl;

import com.example.miniNetflix.domain.dto.MovieDto;
import com.example.miniNetflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @GetMapping
    public ResponseEntity<List<MovieDto>> getAllMovies(@RequestParam(required = false) String genre){
        return ResponseEntity.ok(movieService.getAllMovie(genre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getMovieById(@PathVariable Long id){
        return ResponseEntity.ok(movieService.getMovieById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieDto>> searchMovies(@RequestParam String title){
        return ResponseEntity.ok(movieService.searchMoviesByTitle(title));
    }

}
