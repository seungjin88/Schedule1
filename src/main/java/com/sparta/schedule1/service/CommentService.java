package com.sparta.schedule1.service;

import com.sparta.schedule1.dto.Comment.*;
import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import com.sparta.schedule1.entity.Comment;
import com.sparta.schedule1.entity.Todo;
import com.sparta.schedule1.entity.User;
import com.sparta.schedule1.repository.CommentRepository;
import com.sparta.schedule1.repository.TodoRepository;
import com.sparta.schedule1.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public CommentSaveResponseDto saveComment(Long todoId, CommentSaveRequestDto commentSaveRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 없습니다."));

        User user = userRepository.findById(commentSaveRequestDto.getId()).orElseThrow(() -> new NullPointerException("할 일이 없습니다."));

        Comment newComment = new Comment(commentSaveRequestDto.getContents(), user, todo);

        Comment savedComment = commentRepository.save(newComment);

        return new CommentSaveResponseDto(savedComment.getId(), savedComment.getContents(), new UserSaveResponseDto(user.getId(),user.getUsername(), user.getEmail()));
    }

    public CommentDetailResponseDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다."));

        User user = comment.getUser();

        return new CommentDetailResponseDto(comment.getId(), comment.getContents(), new UserSaveResponseDto(user.getId(),user.getUsername(), user.getEmail()), comment.getCreatedAt(), comment.getModifiedAt());
    }

    public List<CommentSimpleResponseDto> getComments(Long todoId) {
        List<Comment> commentList = commentRepository.findAllByTodoId(todoId);

        List<CommentSimpleResponseDto> dtoList = new ArrayList<>();

        for(Comment comment : commentList) {
            User user = comment.getUser();
            CommentSimpleResponseDto dto = new CommentSimpleResponseDto(comment.getId(), comment.getContents(), new UserSaveResponseDto(user.getId(),user.getUsername(), user.getEmail()), comment.getCreatedAt(), comment.getModifiedAt());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다."));

        comment.updateComment(commentUpdateRequestDto.getContents());

        return new CommentUpdateResponseDto(comment.getId(), comment.getContents());
    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> new NullPointerException("댓글이 없습니다"));

        commentRepository.deleteById(commentId);
        }
    }
