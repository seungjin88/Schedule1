package com.sparta.schedule1.dto.Todo;

import com.sparta.schedule1.dto.User.UserSimpleResponseDto;
import com.sparta.schedule1.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoSimpleResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final int commentSize;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public TodoSimpleResponseDto(Long id, String title, String contents, int commentSize, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.commentSize = commentSize;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
