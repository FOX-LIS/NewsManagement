package com.example.NewsManagement.service;

import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.web.model.NewsFilter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {

    List<News> filterBy(NewsFilter filter);

    List<News> findAll(Pageable pageable);

    News findById(Long id);

    News save(News news);

    News update(News news);

    void deleteById(Long id);

    User getCreatorByNewsId(Long newsId);

}
