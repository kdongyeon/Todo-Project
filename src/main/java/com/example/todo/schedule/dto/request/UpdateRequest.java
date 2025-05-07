package com.example.todo.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateRequest {

    private final String title;

    private final String content;
}
