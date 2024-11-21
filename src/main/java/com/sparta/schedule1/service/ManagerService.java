package com.sparta.schedule1.service;

import com.sparta.schedule1.dto.Manager.ManagerDetailResponseDto;
import com.sparta.schedule1.dto.Manager.ManagerSaveRequestDto;
import com.sparta.schedule1.dto.Manager.ManagerSaveResponseDto;
import com.sparta.schedule1.dto.User.UserSaveResponseDto;
import com.sparta.schedule1.entity.Manager;
import com.sparta.schedule1.entity.Todo;
import com.sparta.schedule1.entity.User;
import com.sparta.schedule1.repository.ManagerRepository;
import com.sparta.schedule1.repository.TodoRepository;
import com.sparta.schedule1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Transactional
    public ManagerSaveResponseDto saveManager(Long todoId, ManagerSaveRequestDto managerSaveRequestDto) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException(("할 일이 없습니다.")));

        User user = userRepository.findById(managerSaveRequestDto.getTodoUserId()).orElseThrow(() -> new NullPointerException("사용자가 없습니다"));

        if (user.getId() != null && ObjectUtils.nullSafeEquals(user.getId(), todo.getUser().getId())) {
            throw new NullPointerException("등록하려고 하는 유저가 일정을 만든 유저가 아닙니다.");
        }
        User manager = userRepository.findById(managerSaveRequestDto.getManagerUserId()).orElseThrow(() -> new NullPointerException("사용자가 없습니다"));

        Manager newManager = new Manager(manager, todo);
        Manager savedManager = managerRepository.save(newManager);

        return new ManagerSaveResponseDto(savedManager.getId());
    }

    public List<ManagerDetailResponseDto> getManagers(Long todoId) {

        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 없습니다"));

        List<Manager> managerList = managerRepository.findByTodoId(todo.getId());

        List<ManagerDetailResponseDto> dtoList = new ArrayList<>();
        for (Manager manager : managerList) {
            User user = manager.getUser();
            dtoList.add(new ManagerDetailResponseDto(manager.getId(), new UserSaveResponseDto(user.getId(), user.getUsername(), user.getEmail())));
        }
        return dtoList;
    }
}
