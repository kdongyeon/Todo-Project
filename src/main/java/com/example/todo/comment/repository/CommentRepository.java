package com.example.todo.comment.repository;

import com.example.todo.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    int countByScheduleId(Long id);

    List<Comment> findByParentCommentId(Long parentId);
    List<Comment> findByParentCommentIdOrderByCreatedAtAsc(Long parentCommentId);

}
