package com.example.todo.comment.service;

import com.example.todo.comment.dto.request.CreateCommentRequest;
import com.example.todo.comment.dto.response.CommentResponse;
import com.example.todo.comment.entity.Comment;
import com.example.todo.comment.repository.CommentRepository;
import com.example.todo.schedule.dto.request.UpdateRequest;
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

        Comment comment = new Comment(
                request.getContent(),
                request.getWriterId(),
                schedule,
                parentComment
        );
        commentRepository.save(comment);
    }

    // 댓글 조회
    public CommentResponse findById(Long scheduleId, Long commentId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 할일 입니다."));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 댓글입니다."));
        return new CommentResponse(comment.getId(),
                                   comment.getSchedule().getId(),
                                   comment.getContent(),
                                   comment.getParentComment() != null ? comment.getParentComment().getId() : null, // 부모 댓글 ID (null 처리)
                                   comment.getCreatedAt()
        );
    }
    // 대댓글 조회
    public CommentResponse findReply(Long scheduleId, Long commentId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 할일 입니다."));

        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 댓글 입니다."));

        return commentTree(parentComment);



    }
    // 재귀적으로 대댓글 트리 구성
    public CommentResponse commentTree(Comment comment){
        CommentResponse commentResponse = new CommentResponse(
                comment.getId(),
                comment.getSchedule().getId(),
                comment.getContent(),
                comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                comment.getCreatedAt()
        );
        List<Comment> children = commentRepository.findByParentCommentIdOrderByCreatedAtAsc(comment.getId());

        for (Comment child : children) {
            commentResponse.getReply().add(commentTree(child));

        }
        return commentResponse;
    }

    public List<CommentResponse> findAll(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 할일 입니다."));

        List<CommentResponse> commentResponseList = new ArrayList<>();
        List<Comment> commentList = commentRepository.findAll();
        for (Comment comment : commentList) {
            commentResponseList.add(new CommentResponse
                            (comment.getId(),
                            schedule.getId(),
                            comment.getContent(),
                            comment.getParentComment() != null ? comment.getParentComment().getId() : null,
                            comment.getCreatedAt()));
        }
        return commentResponseList;

    }

    @Transactional
    public void update(Long commentId, UpdateRequest dto) {
        Comment parentComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("찾을 수 없는 댓글 입니다."));
        parentComment.update(dto.getContent());
    }
}
