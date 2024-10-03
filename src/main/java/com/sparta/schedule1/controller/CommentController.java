package com.sparta.schedule1.controller;

import com.sparta.schedule1.dto.Comment.*;
import com.sparta.schedule1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{todoId}/comments")
    public CommentSaveResponseDto saveComment(@PathVariable Long todoId, @RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        return commentService.saveComment(todoId, commentSaveRequestDto);
    }

    @GetMapping("/comments/{commentId}")
    public CommentDetailResponseDto getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @GetMapping("/{todoId}/comments")
    public List<CommentSimpleResponseDto> getComments(@PathVariable Long todoId) {
        return commentService.getComments(todoId);
    }

    @PutMapping("/comments/{commentId}")
    public CommentUpdateResponseDto updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {
        return commentService.updateComment(commentId, commentUpdateRequestDto);
    }

    @DeleteMapping("/comments/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }
}
