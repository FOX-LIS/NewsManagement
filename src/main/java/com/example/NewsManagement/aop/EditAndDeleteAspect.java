package com.example.NewsManagement.aop;

import com.example.NewsManagement.exception.ActionNotPermittedException;
import com.example.NewsManagement.exception.NotAuthenticatedException;
import com.example.NewsManagement.service.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerMapping;

import java.security.Principal;
import java.util.Map;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class EditAndDeleteAspect {
    private final NewsService newsService;

    @Before("@annotation(Editable)")
    public void editDeleteBefore() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Principal principal = request.getUserPrincipal();
        if (principal == null) {
            throw new NotAuthenticatedException(
                    "Пользователь не аутентифицирован! Редактирование/удаление записи недоступно!");
        }
        String currentUserName = principal.getName();
        var pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Long editedEntityId = Long.parseLong(pathVariables.get("id"));
        String creatorName = newsService.getCreatorByNewsId(editedEntityId).getUserName();
        if (!currentUserName.equals(creatorName)) {
            throw new ActionNotPermittedException("Редактирование/удаление записи разрешено только её создателю!");
        }
    }
}

