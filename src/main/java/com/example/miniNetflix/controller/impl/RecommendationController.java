package com.example.miniNetflix.controller.impl;


import com.example.miniNetflix.domain.dto.MovieDto;
import com.example.miniNetflix.service.IBCFService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
public class RecommendationController {
    private final IBCFService ibcfService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<MovieDto>> getRecommendationsForUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "Action") String genre
    ){
        return ResponseEntity.ok(ibcfService.recommendationsForUser(userId,limit,genre));
    }
}
