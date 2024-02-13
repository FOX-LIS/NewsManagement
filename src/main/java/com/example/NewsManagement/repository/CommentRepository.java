package com.example.NewsManagement.repository;

import com.example.NewsManagement.model.Comment;
import com.example.NewsManagement.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByNewsId(Long newsId);

    Integer countAllByNews(News news);

    Optional<Comment> findById(Long id);

    Comment save(Comment Comment);

    void deleteById(Long id);

}
