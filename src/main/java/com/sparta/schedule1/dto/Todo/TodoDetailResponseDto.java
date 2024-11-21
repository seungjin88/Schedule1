package com.sparta.schedule1.dto.Todo;

import com.sparta.schedule1.dto.User.UserDetailResponseDto;
import com.sparta.schedule1.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoDetailResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final UserDetailResponseDto user;
    private final LocalDateTime createAt;
    private final LocalDateTime modifiedAt;

    public TodoDetailResponseDto(Long id, String title, String contents, User user, LocalDateTime createAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = new UserDetailResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
        this.createAt = createAt;
        this.modifiedAt = modifiedAt;
    }
}
