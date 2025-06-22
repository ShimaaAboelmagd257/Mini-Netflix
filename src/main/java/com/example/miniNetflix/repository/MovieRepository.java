package com.example.miniNetflix.repository;

import com.example.miniNetflix.domain.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity,Long> {
    List<MovieEntity> findByGenresContainingIgnoreCase(String genre);

    List<MovieEntity> findByTitleContainingIgnoreCase(String title);
}
