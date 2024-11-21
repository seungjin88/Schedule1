package com.sparta.schedule1.service;

import com.sparta.schedule1.dto.Todo.*;
import com.sparta.schedule1.dto.User.UserDetailResponseDto;
import com.sparta.schedule1.entity.Todo;
import com.sparta.schedule1.entity.User;
import com.sparta.schedule1.repository.TodoRepository;
import com.sparta.schedule1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
//@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        User user = userRepository.findById(todoSaveRequestDto.getUserId()).orElseThrow(() -> new NoSuchElementException("유저가 없습니다"));

        Todo newTodo = new Todo(
                todoSaveRequestDto.getTitle(),
                todoSaveRequestDto.getContents(),
                user
        );
        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContents(),
                user
        );
    }

    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 업습니다"));

        //log.info(todo.getUser().getUsername());
        return new TodoDetailResponseDto( //객체-> Json(텍스트) : 직렬화
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getUser(),
                todo.getCreatedAt(),
                todo.getModifiedAt());
    }

    public Page<TodoSimpleResponseDto> getTodos(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        Page<Todo> todos = todoRepository.findAllByOrderByModifiedAtDesc(pageable);

        return todos.map(todo -> new TodoSimpleResponseDto(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getComments().size(),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        ));
    }

    @Transactional
    public TodoUpdateResponseDto updateTodo(Long todoId, TodoUpdateRequestDto todoUpdateRequestDto) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 없습니다"));

        User user = userRepository.findById(todoUpdateRequestDto.getUserId()).orElseThrow(() -> new NoSuchElementException("유저가 없습니다"));

        //Todo todo.getUser().getId() == user.getId()
        // 해당 유저가 해당 todo를 작성했는지 확인
        if (!todo.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }

        todo.updateTodo(
                todoUpdateRequestDto.getTitle(),
                todoUpdateRequestDto.getContents()
        );

        return new TodoUpdateResponseDto(todo.getId(), todo.getTitle(), todo.getContents(), user);
    }

    @Transactional
    public void deleteTodo(Long todoId, TodoDeleteDto todoDeleteDto) {
        //1. 유저 Id를 받아서 유저가 있는지 없는지 확인
        //2. 해당하는 자원이(todo) 있는지 확인
        //3. 해당 유저가 해당 todo를 작성했는지 확인
        //(2번과 3번은 같이 처리할 수도 있다. -> todoRepository.findByIdAndUserId(todoId, userId))
        //4. 검증을 통과시 todo지우기

        User user = userRepository.findById(todoDeleteDto.getUserId()).orElseThrow(() -> new NoSuchElementException("유저가 없습니다."));
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 없습니다"));

        if (user.getId() != todo.getUser().getId()){
            throw new RuntimeException("권한이 없습니다.");
        }

        todoRepository.delete(todo);
    }
}
