package com.farhan.blog.service;

import com.farhan.blog.dto.Blogdto;
import com.farhan.blog.dto.UserBlogDto;
import com.farhan.blog.entity.Blog;

import java.util.List;

public interface BlogService {
    public Blogdto addBlog(Blog blog) ;

    public List<Blogdto> getAllBlog();

    public void publishBlog(Integer blogId);

    public Blogdto updateBlog(Blog blog);

    public List<Blogdto> getUserBlogs(Integer userId);

    public void deleteBlog(Integer blogId);

    UserBlogDto getBlogwithUser(Integer blogId);
}
