package com.example.todo.schedule.dto.response;

import com.example.todo.comment.dto.response.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Getter
public class FindResponse {

    private Long id;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<CommentResponse> comments = new ArrayList<>();




}
