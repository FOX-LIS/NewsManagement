package com.example.NewsManagement.service.impl;

import com.example.NewsManagement.exception.EntityNotFoundException;
import com.example.NewsManagement.model.Comment;
import com.example.NewsManagement.model.News;
import com.example.NewsManagement.repository.CommentRepository;
import com.example.NewsManagement.service.CommentService;
import com.example.NewsManagement.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public List<Comment> findAllByNewsId(Long newsId) {
        return commentRepository.findAllByNewsId(newsId);
    }

    @Override
    public Integer countAllByNews(News news) {
        return commentRepository.countAllByNews(news);
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Комментарий с ID {0} не найден!", id)));
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment) {
        Comment existedComment = findById(comment.getId());
        BeanUtils.copyNonNullProperties(comment, existedComment);
        return commentRepository.save(existedComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }

}
