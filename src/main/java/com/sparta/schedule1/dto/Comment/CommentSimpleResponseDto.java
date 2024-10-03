package com.sparta.schedule1.dto.Comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentSimpleResponseDto {

    private final Long id;
    private final String contents;
    private final String user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentSimpleResponseDto(Long id, String contents, String user, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.contents = contents;
        this.user = user;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
