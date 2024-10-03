package com.sparta.schedule1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Todo extends TimeStamped{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;
    private String user;

    @OneToMany(mappedBy = "todo", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public Todo(String title, String contents, String user) {
        this.title = title;
        this.contents = contents;
        this.user = user;
    }

    public void updateTodo(String title, String contents, String user) {
        this.title = title;
        this.contents = contents;
        this.user = user;

    }
}
