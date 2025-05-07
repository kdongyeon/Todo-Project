package com.example.todo.comment.service;

import com.example.todo.comment.dto.request.CreateCommentRequest;
import com.example.todo.comment.dto.response.CommentResponse;
import com.example.todo.comment.entity.Comment;
import com.example.todo.comment.repository.CommentRepository;
import com.example.todo.schedule.entity.Schedule;
import com.example.todo.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public void create(Long scheduleId, CreateCommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 할일 입니다."));

        Comment parentComment = null;
        if (request.getParentCommentId() != null) {
            parentComment = commentRepository.findById(request.getParentCommentId())
                    .orElseThrow(() -> new RuntimeException("부모 댓글을 찾을 수 없습니다."));
        }
        // 댓글 생성
        Comment comment = new Comment(
                request.getContent(),
                request.getWriterId(),
                schedule,
                parentComment
        );
        commentRepository.save(comment);
    }


    public CommentResponse findById(Long scheduleId, Long commentId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 할일 입니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 댓글입니다."));
        return new CommentResponse(comment.getId(),
                                   schedule.getId(),
                                   comment.getContent(),
                                   comment.getParentComment() != null ? comment.getParentComment().getId() : null, // 부모 댓글 ID (null 처리)
                                   comment.getCreatedAt()
        );
    }

    public List<CommentResponse> findReply(Long scheduleId, Long commentId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 할일 입니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 댓글입니다."));


        List<Comment> commentList = commentRepository.findCommentId(commentId);
        List<CommentResponse> commentResponseList = new ArrayList<>();


    }
}
