package com.example.NewsManagement.mapper;

import com.example.NewsManagement.model.Category;
import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.web.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface CategoryMapper {

    Category requestToCategory(UpsertCategoryRequest request);
    
    @Mapping(source = "categoryId", target = "id")
    Category requestToCategory(Long categoryId, UpsertCategoryRequest request);
    
    CategoryResponse categoryToResponse(Category category);
    
    default CategoryListResponse categoryListToCategoryResponseList(List<Category> category){
        CategoryListResponse response = new CategoryListResponse();
        response.setCategories(category.stream().map(this::categoryToResponse).collect(Collectors.toList()));
        return response;
    }

}
