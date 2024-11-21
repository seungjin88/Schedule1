package com.sparta.schedule1.dto.Todo;

import com.sparta.schedule1.dto.User.UserDetailResponseDto;
import com.sparta.schedule1.entity.User;
import lombok.Getter;

@Getter
public class TodoUpdateResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final UserDetailResponseDto user;


    public TodoUpdateResponseDto(Long id, String title, String contents, User user) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = new UserDetailResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());;
    }
}
