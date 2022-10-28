package com.example.app.service.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PostGroupRelInfo {

    @Value("#{target.id.postsId}")
    Long getPostsId();

    String getContent();

    @Value("#{target.posts.authorType}")
    String getAuthorType();
}
