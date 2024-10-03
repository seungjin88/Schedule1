package com.sparta.schedule1.dto.Comment;

import lombok.Getter;

@Getter
public class CommentUpdateResponseDto {

    private final Long id;
    private final String contents;
    private final String user;

    public CommentUpdateResponseDto(Long id, String contents, String user) {
        this.id = id;
        this.contents = contents;
        this.user = user;
    }
}
