package com.example.NewsManagement.web.model;

import com.example.NewsManagement.model.Category;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.validation.NewsFilterValid;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@NewsFilterValid
public class NewsFilter {

    private Integer pageSize;

    private Integer pageNumber;

    private Category category;

    private User creator;

}
