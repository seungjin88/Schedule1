package com.sparta.schedule1.controller;

import com.sparta.schedule1.dto.Manager.ManagerDetailResponseDto;
import com.sparta.schedule1.dto.Manager.ManagerSaveRequestDto;
import com.sparta.schedule1.dto.Manager.ManagerSaveResponseDto;
import com.sparta.schedule1.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("todos")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping("/{todoId}/managers")
    public ResponseEntity<ManagerSaveResponseDto> saveManager(@PathVariable Long todoId, @RequestBody ManagerSaveRequestDto managerSaveRequestDto) {
        return ResponseEntity.ok(managerService.saveManager(todoId, managerSaveRequestDto));
    }

    @GetMapping("/{todoId}/managers")
    public ResponseEntity<List<ManagerDetailResponseDto>> getMembers(@PathVariable Long todoId) {
        return ResponseEntity.ok(managerService.getManagers(todoId));
    }
}
