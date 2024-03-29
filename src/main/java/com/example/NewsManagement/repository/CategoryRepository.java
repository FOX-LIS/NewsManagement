package com.example.NewsManagement.repository;

import com.example.NewsManagement.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long id);

    Category save(Category Category);

    void deleteById(Long id);

}
