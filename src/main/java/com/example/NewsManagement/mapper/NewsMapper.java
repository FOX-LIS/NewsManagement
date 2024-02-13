package com.example.NewsManagement.mapper;

import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.web.model.NewsListResponse;
import com.example.NewsManagement.web.model.NewsResponse;
import com.example.NewsManagement.web.model.UpsertNewsRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class})
public interface NewsMapper {

    News requestToNews(UpsertNewsRequest request);

    @Mapping(source = "newsId", target = "id")
    News requestToNews(Long newsId, UpsertNewsRequest request);

    NewsResponse newsToResponse(News news);

    default NewsListResponse newsListToNewsResponseList(List<News> news){
        NewsListResponse response = new NewsListResponse();
        response.setNewsList(news.stream().map(this::newsToResponse).collect(Collectors.toList()));
        return response;
    }

}
