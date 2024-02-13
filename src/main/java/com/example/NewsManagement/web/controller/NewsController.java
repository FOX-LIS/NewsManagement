package com.example.NewsManagement.web.controller;

import com.example.NewsManagement.aop.Editable;
import com.example.NewsManagement.mapper.NewsMapper;
import com.example.NewsManagement.mapper.UserMapper;
import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.service.NewsService;
import com.example.NewsManagement.service.UserService;
import com.example.NewsManagement.web.model.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    private final NewsMapper newsMapper;

    @GetMapping("/filter")
    public ResponseEntity<NewsListResponse> filterBy(@Valid NewsFilter filter){
        return ResponseEntity.ok(
                newsMapper.newsListToNewsResponseList(
                        newsService.filterBy(filter)
                )
        );
    }

    @GetMapping
    public ResponseEntity<NewsListResponse> findAll(Pageable pageable){
        return ResponseEntity.ok(
                newsMapper.newsListToNewsResponseList(
                        newsService.findAll(pageable)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(newsMapper.newsToResponse(
                newsService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<NewsResponse> create(@RequestBody @Valid UpsertNewsRequest request){
        News newNews = newsService.save(newsMapper.requestToNews(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(newsMapper.newsToResponse(newNews));
    }

    @PutMapping("/{id}")
    @Editable
    public ResponseEntity<NewsResponse> update(@PathVariable("id") Long newsId, @RequestBody @Valid UpsertNewsRequest request){
        News updatedNews = newsService.update(newsMapper.requestToNews(newsId, request));
        return ResponseEntity.ok(newsMapper.newsToResponse(updatedNews));
    }
    
    @DeleteMapping("/{id}")
    @Editable
    public ResponseEntity<Void> delete(@PathVariable Long id){
        newsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
