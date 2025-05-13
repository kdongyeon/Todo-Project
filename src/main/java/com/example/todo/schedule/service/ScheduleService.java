package com.example.todo.schedule.service;

import com.example.todo.comment.dto.response.CommentResponse;
import com.example.todo.comment.entity.Comment;
import com.example.todo.comment.repository.CommentRepository;
import com.example.todo.comment.service.CommentService;
import com.example.todo.schedule.dto.request.CreateRequest;
import com.example.todo.schedule.dto.request.UpdateRequest;
import com.example.todo.schedule.dto.response.FindResponse;
import com.example.todo.schedule.dto.response.FindResponses;
import com.example.todo.schedule.entity.Schedule;
import com.example.todo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;



    public void create(CreateRequest dto) {

        Schedule schedule = new Schedule(dto.getTitle(), dto.getContent());

        scheduleRepository.save(schedule);
    }



    @Transactional
    public FindResponse findById(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("할일을 찾을 수 없습니다."));

        List<Comment> parentComments = commentRepository.findByScheduleIdAndParentCommentIsNull(scheduleId);

        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : parentComments) {
            commentResponses.add(commentService.commentTree(comment));
        }

        return new FindResponse(schedule.getId(),
                                schedule.getTitle(),
                                schedule.getContent(),
                                schedule.getCreatedAt(),
                                schedule.getUpdatedAt(),
                                commentResponses
        );

    }

    public List<FindResponses> findAll() {
        List<FindResponses> findResponses = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findAll();
        for (Schedule schedule : schedules) {
            int commentCount = commentRepository.countByScheduleId(schedule.getId());
            findResponses.add(new FindResponses(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getCreatedAt(),
                    schedule.getUpdatedAt(),
                    commentCount
            ));

        }
        return findResponses;
    }

    @Transactional
    public void update(Long scheduleId, UpdateRequest dto) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("할일을 찾을 수 없습니다."));

        schedule.update(dto.getTitle(), dto.getContent());

    }

    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("할일을 찾을 수 없습니다."));
        scheduleRepository.delete(schedule);
    }
}
