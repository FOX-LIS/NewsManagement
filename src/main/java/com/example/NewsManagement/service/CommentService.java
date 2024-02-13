package com.example.NewsManagement.service;

import com.example.NewsManagement.model.Comment;
import com.example.NewsManagement.model.News;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    
    List<Comment> findAllByNewsId(Long newsId);

    Integer countAllByNews(News news);
    
    Comment findById(Long id);
    
    Comment save(Comment comment);
    
    Comment update(Comment comment);
    
    void deleteById(Long id);
    
}
