package com.sparta.schedule1.dto.Manager;

import lombok.Getter;

@Getter
public class ManagerSaveRequestDto {

    private Long todoUserId;
    private Long managerUserId;
    private Long todoId;
}
