package com.example.NewsManagement.web.model;

import lombok.Data;

@Data
public class UpsertUserRequest {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;
}
