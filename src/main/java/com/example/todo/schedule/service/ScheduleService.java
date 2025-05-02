package com.example.todo.schedule.service;

import com.example.todo.schedule.dto.request.CreateRequest;
import com.example.todo.schedule.dto.response.FindResponse;
import com.example.todo.schedule.entity.Schedule;
import com.example.todo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public void create(CreateRequest dto) {

        Schedule schedule = new Schedule(dto.getTitle(), dto.getContent());

        scheduleRepository.save(schedule);
    }


    public FindResponse findById(Long scheduleId) {

    }
}
