package com.example.miniNetflix.repository;

import com.example.miniNetflix.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
    List<MovieEntity> findByGenresContainingIgnoreCase(String genre);
}
