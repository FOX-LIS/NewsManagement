package com.example.NewsManagement.web.model;

import com.example.NewsManagement.model.Comment;
import com.example.NewsManagement.model.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UpsertNewsRequest {

    private Long id;

    private String header;

    private String text;

    private User creator;

    private List<Comment> comments = new ArrayList<>();

}
