package com.example.NewsManagement.mapper;

import com.example.NewsManagement.model.User;
import com.example.NewsManagement.web.model.UpsertUserRequest;
import com.example.NewsManagement.web.model.UserListResponse;
import com.example.NewsManagement.web.model.UserResponse;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@DecoratedWith(UserMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User requestToUser(UpsertUserRequest request);

    @Mapping(source = "userId", target = "id")
    User requestToUser(Long userId, UpsertUserRequest request);

    UserResponse userToResponse(User user);
    List<UserResponse> userListToResponseList(List<User>users);

    default UserListResponse userListToUserResponseList(List<User> users) {
        UserListResponse response = new UserListResponse();
        response.setUsers(userListToResponseList(users));
        return response;
    }
}

