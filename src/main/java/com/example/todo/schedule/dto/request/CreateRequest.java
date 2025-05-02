package com.example.todo.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateRequest {

    private String title;

    private String content;



}
