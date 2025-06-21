package com.example.miniNetflix.controller.impl;


import com.example.miniNetflix.domain.dto.RatingDto;
import com.example.miniNetflix.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDto> submitRating(@RequestBody RatingDto ratingDto){
        return ResponseEntity.ok(ratingService.saveRating(ratingDto));

    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RatingDto>> getRatingsByUser(@PathVariable Long userId){
        return ResponseEntity.ok(ratingService.getRatingsByUser(userId));
    }
}
