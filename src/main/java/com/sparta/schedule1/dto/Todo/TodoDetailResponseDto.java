package com.sparta.schedule1.dto.Todo;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoDetailResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String user;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public TodoDetailResponseDto(Long id, String title, String contents, String user, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
