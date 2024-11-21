package com.sparta.schedule1.repository;

import com.sparta.schedule1.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    List<Manager> findByTodoId(Long id);
}
