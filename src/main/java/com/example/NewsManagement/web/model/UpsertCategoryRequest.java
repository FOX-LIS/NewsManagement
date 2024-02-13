package com.example.NewsManagement.web.model;

import com.example.NewsManagement.model.News;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpsertCategoryRequest {

    private Long id;

    private String name;

    private List<News> newsList = new ArrayList<>();

}
