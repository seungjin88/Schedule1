package com.sparta.schedule1.service;

import com.sparta.schedule1.dto.Comment.*;
import com.sparta.schedule1.entity.Comment;
import com.sparta.schedule1.entity.Todo;
import com.sparta.schedule1.repository.CommentRepository;
import com.sparta.schedule1.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(Long todoId, CommentSaveRequestDto commentSaveRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 없습니다."));

        Comment newComment = new Comment(commentSaveRequestDto.getContents(), commentSaveRequestDto.getUser(), todo);

        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(savedComment.getId(), savedComment.getContents(), savedComment.getUser());
    }

    public CommentDetailResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다."));

        return new CommentDetailResponseDto(comment.getId(), comment.getContents(), comment.getUser(), comment.getCreatedAt(), comment.getModifiedAt());
    }

    public List<CommentSimpleResponseDto> getComments(Long todoId) {
        List<Comment> commentList = commentRepository.findAllByTodoId(todoId);

        List<CommentSimpleResponseDto> dtoList = new ArrayList<>();

        for(Comment comment : commentList) {
            CommentSimpleResponseDto dto = new CommentSimpleResponseDto(comment.getId(), comment.getContents(), comment.getUser(), comment.getCreatedAt(), comment.getModifiedAt());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다."));

        comment.updateComment(commentUpdateRequestDto.getContents(), commentUpdateRequestDto.getUser());

        return new CommentUpdateResponseDto(comment.getId(), comment.getContents(), comment.getUser());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다"));

        commentRepository.deleteById(commentId);
        }
    }
