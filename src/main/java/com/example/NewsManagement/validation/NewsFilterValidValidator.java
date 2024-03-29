package com.example.NewsManagement.validation;

import com.example.NewsManagement.web.model.NewsFilter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.ObjectUtils;

public class NewsFilterValidValidator implements ConstraintValidator<NewsFilterValid, NewsFilter> {

    @Override
    public boolean isValid(NewsFilter newsFilter, ConstraintValidatorContext constraintValidatorContext) {
        if(ObjectUtils.anyNull(newsFilter.getPageNumber(), newsFilter.getPageSize())){
            return false;
        }
        return newsFilter.getCategory() != null && newsFilter.getCreator() != null;
    }

}
