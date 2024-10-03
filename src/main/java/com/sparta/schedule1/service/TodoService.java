package com.sparta.schedule1.service;

import com.sparta.schedule1.dto.Todo.*;
import com.sparta.schedule1.entity.Todo;
import com.sparta.schedule1.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public TodoSaveResponseDto saveTodo(TodoSaveRequestDto todoSaveRequestDto) {
        Todo newTodo = new Todo(
                todoSaveRequestDto.getTitle(),
                todoSaveRequestDto.getContents(),
                todoSaveRequestDto.getUser()
        );
        Todo savedTodo = todoRepository.save(newTodo);

        return new TodoSaveResponseDto(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getContents(),
                savedTodo.getUser()
        );
    }

    public TodoDetailResponseDto getTodo(Long todoId) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 업습니다"));

        return new TodoDetailResponseDto(
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
                todo.getUser(),
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

        todo.updateTodo(
                todoUpdateRequestDto.getTitle(),
                todoUpdateRequestDto.getContents(),
                todoUpdateRequestDto.getUser()
        );
        return new TodoUpdateResponseDto(todo.getId(), todo.getTitle(), todo.getContents(), todo.getUser());
    }

    @Transactional
    public void deleteTodo(Long todoId) {
        todoRepository.findById(todoId).orElseThrow(() -> new NullPointerException("할 일이 없습니다"));

        todoRepository.deleteById(todoId);
    }
}
