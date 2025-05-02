package com.example.todo.schedule.entity;

import com.example.todo.comment.entity.Comment;
import com.example.todo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name ="schedule")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedule extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();

    // 생성 엔티티
    public Schedule(String title, String content) {
        this.title = title;
        this.content = content;
    }



}
