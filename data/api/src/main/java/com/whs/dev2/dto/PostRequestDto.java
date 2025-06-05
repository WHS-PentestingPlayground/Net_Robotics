package com.whs.dev2.dto;

import lombok.Getter;
import lombok.Setter;
import com.whs.dev2.entity.Post;

@Getter
@Setter
public class PostRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}