package com.example.todo.schedule.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
public class FindResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private int commentCount;

}
