package com.example.todo.comment.dto.response;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class CommentResponse {

    private final Long commentId;
    private final Long scheduleId;
    private final String content;
    private final Long parentId;
    private final LocalDateTime createdAt;
    private final List<CommentResponse> reply = new ArrayList<>();

}
