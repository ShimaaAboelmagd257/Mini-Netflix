package com.example.miniNetflix.repository;

import com.example.miniNetflix.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
