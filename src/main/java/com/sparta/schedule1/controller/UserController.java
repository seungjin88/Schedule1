package com.sparta.schedule1.controller;

import com.sparta.schedule1.dto.User.UserDetailResponseDto;
import com.sparta.schedule1.dto.User.UserSaveRequestDto;
import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import com.sparta.schedule1.dto.User.UserSimpleResponseDto;
import com.sparta.schedule1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserSaveResponseDto saveUser(@RequestBody UserSaveRequestDto userSaveRequestDto) {
        return userService.saveUser(userSaveRequestDto);
    }

    @GetMapping("/{userId}")
    public UserDetailResponseDto getUser(@PathVariable Long userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    public List<UserSimpleResponseDto> getUsers() {
        return userService.getUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}
