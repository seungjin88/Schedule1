package com.sparta.schedule1.dto.Manager;

import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import lombok.Getter;

@Getter
public class ManagerDetailResponseDto {
    private final Long id;
    private final UserSaveResponseDto user;

    public ManagerDetailResponseDto(Long id, UserSaveResponseDto user) {
        this.id = id;
        this.user = user;
    }
}
