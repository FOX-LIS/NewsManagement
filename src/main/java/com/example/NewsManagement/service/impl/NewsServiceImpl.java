package com.example.NewsManagement.service.impl;

import com.example.NewsManagement.exception.EntityNotFoundException;
import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.repository.CommentRepository;
import com.example.NewsManagement.repository.NewsRepository;
import com.example.NewsManagement.service.NewsService;
import com.example.NewsManagement.utils.BeanUtils;
import com.example.NewsManagement.web.model.NewsFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private final CommentRepository commentRepository;

    @Override
    public List<News> filterBy(NewsFilter filter) {
        return newsRepository.findAllByCategoryAndCreator(filter.getCategory(), filter.getCreator(),
                PageRequest.of(
                        filter.getPageNumber(), filter.getPageSize()
                )).getContent();
    }

    @Override
    public List<News> findAll(Pageable pageable) {
        List<News> newsList = newsRepository.findAll(pageable).getContent();
        newsList.forEach(news -> news.setCommentsCount(commentRepository.countAllByNews(news)));
        return newsList;
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        MessageFormat.format("Новость с ID {0} не найдена!", id)));
    }

    @Override
    public News save(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News update(News news) {
        News existedNews = findById(news.getId());
        BeanUtils.copyNonNullProperties(news, existedNews);
        return newsRepository.save(existedNews);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    @Override
    public User getCreatorByNewsId(Long newsId) {
        News foundNews = newsRepository.findById(newsId).orElseThrow();
        return foundNews.getCreator();
    }
}
