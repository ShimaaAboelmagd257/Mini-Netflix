package com.example.miniNetflix.service;

import com.example.miniNetflix.domain.dto.MovieDto;

import java.util.List;

public interface IBCFService {
    public List<MovieDto> recommendationsForUser(Long userId, int limit, String genre) ;
}
