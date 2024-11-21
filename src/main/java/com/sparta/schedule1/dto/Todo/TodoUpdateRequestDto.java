package com.sparta.schedule1.dto.Todo;

import com.sparta.schedule1.entity.User;
import lombok.Getter;

@Getter
public class TodoUpdateRequestDto {

    private String title;
    private String contents;
    private Long userId;
}
