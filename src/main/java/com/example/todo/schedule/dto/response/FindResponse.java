package com.example.todo.schedule.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FindResponse {

    private final Long id;

    private final String title;

    private final String content;
}
