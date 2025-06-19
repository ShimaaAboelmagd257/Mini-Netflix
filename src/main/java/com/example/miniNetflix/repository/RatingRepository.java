package com.example.miniNetflix.repository;

import com.example.miniNetflix.domain.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Rating.RatingId> {
}
