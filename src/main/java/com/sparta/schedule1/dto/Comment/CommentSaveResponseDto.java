package com.sparta.schedule1.dto.Comment;

import lombok.Getter;

@Getter
public class CommentSaveResponseDto {

    private final Long id;
    private final String contents;
    private final String user;

    public CommentSaveResponseDto(Long id, String contents, String user) {
        this.id = id;
        this.contents = contents;
        this.user = user;
    }
}
