package com.example.NewsManagement.repository;

import com.example.NewsManagement.model.Category;
import com.example.NewsManagement.model.News;
import com.example.NewsManagement.model.User;
import com.example.NewsManagement.web.model.NewsFilter;
import org.springframework.data.jpa.domain.Specification;

public interface NewsSpecification {

    static Specification<News> withFilter(NewsFilter newsFilter){
        return Specification.where(byCategory(newsFilter.getCategory()))
                .and(byCreator(newsFilter.getCreator()));
    }

    static Specification<News> byCategory(Category category){
        return ((root, query, criteriaBuilder) -> {
            if(category == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("category"), category);
        });
    }

    static Specification<News> byCreator(User creator){
        return ((root, query, criteriaBuilder) -> {
            if(creator == null){
                return null;
            }
            return criteriaBuilder.equal(root.get("creator"), creator);
        });
    }

}
