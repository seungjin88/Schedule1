package com.sparta.schedule1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends TimeStamped{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    private String user;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "todo_id", nullable = false)
    private Todo todo;

    public Comment(String contents, String user, Todo todo) {
        this.contents = contents;
        this.user = user;
        this.todo = todo;
    }

    public void updateComment(String contents, String user) {
        this.contents = contents;
        this.user = user;
    }
}
