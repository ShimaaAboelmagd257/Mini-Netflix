package com.example.miniNetflix.repository;

import com.example.miniNetflix.domain.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Rating.RatingId> {
    List<Rating> findByIdUserId(Long userId);
}
