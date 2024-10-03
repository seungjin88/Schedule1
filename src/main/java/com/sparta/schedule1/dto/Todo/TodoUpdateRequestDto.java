package com.sparta.schedule1.dto.Todo;

import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {

    private String title;
    private String contents;
    private String user;

    public TodoUpdateRequestDto(String title, String contents, String user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
}
