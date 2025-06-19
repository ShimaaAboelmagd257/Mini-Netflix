package com.example.miniNetflix.repository;

import com.example.miniNetflix.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
}
