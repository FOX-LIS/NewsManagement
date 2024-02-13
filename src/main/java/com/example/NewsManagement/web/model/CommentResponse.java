package com.example.NewsManagement.web.model;

import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import lombok.Data;

@Data
public class CommentResponse {

    private Long id;

    private String text;

    private User creator;

    private News news;

}
