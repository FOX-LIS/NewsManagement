package com.example.NewsManagement.service;

import com.example.NewsManagement.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll(Pageable pageable);

    User findById(Long id);

    User save(User user);

    User update(User user);

    void deleteById(Long id);

}
