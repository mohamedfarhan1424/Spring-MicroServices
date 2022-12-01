package com.farhan.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBlogDto {

    private UserDto user;
    private Blogdto blog;
}
