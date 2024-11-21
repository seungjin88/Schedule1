package com.sparta.schedule1.controller;

import com.sparta.schedule1.dto.Todo.TodoSimpleResponseDto;
import com.sparta.schedule1.dto.Todo.*;
import com.sparta.schedule1.entity.Todo;
import com.sparta.schedule1.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoSaveResponseDto saveTodo(@RequestBody TodoSaveRequestDto todoSaveRequestDto) {
        return todoService.saveTodo(todoSaveRequestDto);
    }

    @GetMapping("/{todoId}")
    public TodoDetailResponseDto getTodo(@PathVariable Long todoId) {
        return todoService.getTodo(todoId);
    }

    @GetMapping
    public Page<TodoSimpleResponseDto> getTodos(
            @RequestParam(defaultValue = "1", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size) {
        return todoService.getTodos(page, size);
    }

    @PutMapping("/{todoId}")
    public TodoUpdateResponseDto updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequestDto todoUpdateRequestDto) {
        return todoService.updateTodo(todoId, todoUpdateRequestDto);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodo(@PathVariable Long todoId, @RequestBody TodoDeleteDto todoDeleteDto) {
        todoService.deleteTodo(todoId, todoDeleteDto);
    }
}