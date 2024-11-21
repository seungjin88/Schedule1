package com.sparta.schedule1.dto.Todo;

import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import com.sparta.schedule1.entity.User;
import lombok.Getter;

@Getter
public class TodoSaveResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final UserSaveResponseDto user;

    public TodoSaveResponseDto(Long id, String title, String contents, User user) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = new UserSaveResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
