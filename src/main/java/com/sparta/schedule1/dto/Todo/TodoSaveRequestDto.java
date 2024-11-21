package com.sparta.schedule1.dto.Todo;

import lombok.Getter;

@Getter
public class TodoSaveRequestDto {

    private String title;
    private String contents;
    private Long userId;
}
