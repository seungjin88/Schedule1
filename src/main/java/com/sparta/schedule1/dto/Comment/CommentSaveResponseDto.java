package com.sparta.schedule1.dto.Comment;

import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import lombok.Getter;

@Getter
public class CommentSaveResponseDto {

    private final Long id;
    private final String contents;
    private final UserSaveResponseDto user;

    public CommentSaveResponseDto(Long id, String contents, UserSaveResponseDto user) {
        this.id = id;
        this.contents = contents;
        this.user = user;
    }
}
