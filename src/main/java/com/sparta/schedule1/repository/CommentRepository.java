package com.sparta.schedule1.repository;

import com.sparta.schedule1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByTodoId(Long todoId);
}
