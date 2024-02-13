package com.example.NewsManagement.mapper;

import com.example.NewsManagement.model.Comment;
import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.web.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface CommentMapper {

    Comment requestToComment(UpsertCommentRequest request);

    @Mapping(source = "commentId", target = "id")
    Comment requestToComment(Long commentId, UpsertCommentRequest request);

    CommentResponse commentToResponse(Comment comment);

    default CommentListResponse commentListToCommentResponseList(List<Comment> comment){
        CommentListResponse response = new CommentListResponse();
        response.setComments(comment.stream().map(this::commentToResponse).collect(Collectors.toList()));
        return response;
    }

}
