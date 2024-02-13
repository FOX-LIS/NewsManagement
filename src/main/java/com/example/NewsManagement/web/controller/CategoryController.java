package com.example.NewsManagement.web.controller;

import com.example.NewsManagement.mapper.CategoryMapper;
import com.example.NewsManagement.mapper.UserMapper;
import com.example.NewsManagement.model.Category;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.service.CategoryService;
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
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<CategoryListResponse> findAll(Pageable pageable){
        return ResponseEntity.ok(
                categoryMapper.categoryListToCategoryResponseList(
                        categoryService.findAll(pageable)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(categoryMapper.categoryToResponse(
                categoryService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody @Valid UpsertCategoryRequest request){
        Category newCategory = categoryService.save(categoryMapper.requestToCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.categoryToResponse(newCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable("id") Long categoryId, @RequestBody @Valid UpsertCategoryRequest request){
        Category updatedCategory = categoryService.update(categoryMapper.requestToCategory(categoryId, request));
        return ResponseEntity.ok(categoryMapper.categoryToResponse(updatedCategory));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
