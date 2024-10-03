package com.sparta.schedule1.dto.Todo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoSimpleResponseDto {

    private final Long id;
    private final String user;
    private final String title;
    private final String contents;
    private final int commentSize;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoSimpleResponseDto(Long id, String user, String title, String contents, int commentSize, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.commentSize = commentSize;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
