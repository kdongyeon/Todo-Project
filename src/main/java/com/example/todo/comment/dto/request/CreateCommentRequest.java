package com.example.todo.comment.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateCommentRequest {


    private final String content;
    private final Long writerId;
    private final Long parentCommentId;


}
