package com.example.NewsManagement.mapper;

import com.example.NewsManagement.model.User;
import com.example.NewsManagement.service.UserService;
import com.example.NewsManagement.web.model.UpsertUserRequest;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserMapperDelegate implements UserMapper{

    @Autowired
    private UserService userService;

    @Override
    public User requestToUser(UpsertUserRequest request){
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAge(request.getAge());
        return user;
    }

    @Override
    public User requestToUser(Long userId, UpsertUserRequest request) {
        User user = requestToUser(request);
        user.setId(userId);
        return user;
    }
}
