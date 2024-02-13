package com.example.NewsManagement.repository;

import com.example.NewsManagement.model.Category;
import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {

    Page<News> findAllByCategoryAndCreator(Category category, User creator, Pageable pageable);

    Page<News> findAll(Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"comments"})
    Optional<News> findById(Long id);

    News save(News news);

    void deleteById(Long id);

}
