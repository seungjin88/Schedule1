package com.sparta.schedule1.repository;

import com.sparta.schedule1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
