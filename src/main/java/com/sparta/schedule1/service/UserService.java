package com.sparta.schedule1.service;

import com.sparta.schedule1.dto.User.UserDetailResponseDto;
import com.sparta.schedule1.dto.User.UserSaveRequestDto;
import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import com.sparta.schedule1.dto.User.UserSimpleResponseDto;
import com.sparta.schedule1.entity.User;
import com.sparta.schedule1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserSaveResponseDto saveUser(UserSaveRequestDto userSaveRequestDto) {
        User user = new User(userSaveRequestDto.getUsername(), userSaveRequestDto.getEmail());

        User savedUser = userRepository.save(user);

        return new UserSaveResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public UserDetailResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NullPointerException("유저가 없습니다"));

        return new UserDetailResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }

    public List<UserSimpleResponseDto> getUsers() {
        List<User> userList = userRepository.findAll();

        List<UserSimpleResponseDto> dtoList = new ArrayList<>();

        for(User user : userList) {
            UserSimpleResponseDto dto = new UserSimpleResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.findById(userId).orElseThrow(() -> new NullPointerException("유저가 없습니다."));

        userRepository.deleteById(userId);
    }
}
