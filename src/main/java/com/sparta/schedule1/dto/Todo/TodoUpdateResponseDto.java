package com.sparta.schedule1.dto.Todo;

import lombok.Getter;

@Getter
public class TodoUpdateResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final String user;


    public TodoUpdateResponseDto(Long id, String title, String contents, String user) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
}