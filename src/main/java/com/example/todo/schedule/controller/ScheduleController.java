package com.example.todo.schedule.controller;

import com.example.todo.schedule.dto.request.CreateRequest;
import com.example.todo.schedule.dto.request.UpdateRequest;
import com.example.todo.schedule.dto.response.FindResponse;
import com.example.todo.schedule.dto.response.FindResponses;
import com.example.todo.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    //일정 전체 조회
    @GetMapping
    ResponseEntity<List<FindResponses>> findAll(){

        scheduleService.findAll();
        return ResponseEntity.ok(scheduleService.findAll());
    }
    @PatchMapping("/{scheduleId}")
    ResponseEntity<String> update(
            @PathVariable Long scheduleId,
            @RequestBody UpdateRequest dto)
    {
        scheduleService.update(scheduleId, dto);
        return ResponseEntity.ok("일정 수정 완료");
    }
    @DeleteMapping("/{scheduleId}")
    ResponseEntity<Void> delete(@PathVariable Long scheduleId){

        scheduleService.delete(scheduleId);
        return ResponseEntity.noContent().build();
    }

}
