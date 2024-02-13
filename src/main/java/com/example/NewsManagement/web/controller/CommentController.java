package com.example.NewsManagement.web.controller;

import com.example.NewsManagement.aop.Editable;
import com.example.NewsManagement.mapper.CommentMapper;
import com.example.NewsManagement.mapper.UserMapper;
import com.example.NewsManagement.model.Comment;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.service.CommentService;
import com.example.NewsManagement.service.UserService;
import com.example.NewsManagement.web.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @GetMapping("/{newsId}")
    public ResponseEntity<CommentListResponse> findAllByNewsId(@PathVariable Long newsId) {
        return ResponseEntity.ok(
                commentMapper.commentListToCommentResponseList(
                        commentService.findAllByNewsId(newsId)
                )
        );
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request){
        Comment newComment = commentService.save(commentMapper.requestToComment(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(commentMapper.commentToResponse(newComment));
    }

    @PutMapping("/{id}")
    @Editable
    public ResponseEntity<CommentResponse> update(@PathVariable("id") Long commentId, @RequestBody @Valid UpsertCommentRequest request){
        Comment updatedComment = commentService.update(commentMapper.requestToComment(commentId, request));
        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }
    
    @DeleteMapping("/{id}")
    @Editable
    public ResponseEntity<Void> delete(@PathVariable Long id){
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
