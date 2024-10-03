package com.sparta.schedule1.dto.Comment;

import lombok.Getter;

@Getter
public class CommentSaveRequestDto {

    private Long id;
    private String contents;
    private String user;
}
