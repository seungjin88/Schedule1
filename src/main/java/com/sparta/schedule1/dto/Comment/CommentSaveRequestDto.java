package com.sparta.schedule1.dto.Comment;

import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

    private Long id;
    private String contents;
}
