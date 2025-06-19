package com.example.miniNetflix.repository;

import com.example.miniNetflix.domain.entity.LoadStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoadStatusRepository extends JpaRepository<LoadStatus,String> {
}
