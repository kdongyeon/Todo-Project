package com.example.todo.comment.controller;

import com.example.todo.comment.dto.request.CreateCommentRequest;
import com.example.todo.comment.dto.response.CommentResponse;
import com.example.todo.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/schedules/{scheduleId}/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 등록
    @PostMapping
    public ResponseEntity<Void> create(
            @PathVariable Long scheduleId,
            @RequestBody CreateCommentRequest request
    ){
        commentService.create(scheduleId,request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // 댓글 조회
    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> findById(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId
    ){
        return ResponseEntity.ok(commentService.findById(scheduleId,commentId));
    }
    // 대댓글 조회
    @GetMapping("/{commentId}/replies")
    public ResponseEntity<List<CommentResponse>> findReply(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId
    ){
        return ResponseEntity.ok(commentService.findReply(scheduleId, commentId));
    }
    // 대글 전체 조회
    @GetMapping()
    public ResponseEntity<List<CommentResponse>> findAll(@PathVariable Long scheduleId){
        return ResponseEntity.ok(commentService.findAll(scheduleId));
    }
}
