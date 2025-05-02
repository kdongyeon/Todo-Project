package com.example.todo.schedule.controller;

import com.example.todo.schedule.dto.request.CreateRequest;
import com.example.todo.schedule.dto.response.FindResponse;
import com.example.todo.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 등록
    @PostMapping
    ResponseEntity<Void> create(@RequestBody CreateRequest dto){

        scheduleService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //일정 조회
    @GetMapping("/{scheduleId}")
    ResponseEntity<FindResponse> findById(@PathVariable Long scheduleId){

        scheduleService.findById(scheduleId);
        return ResponseEntity.ok(scheduleService.findById(scheduleId));
    }

}
