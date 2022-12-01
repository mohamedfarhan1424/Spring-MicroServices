package com.farhan.blog.service;

import com.farhan.blog.dto.Blogdto;
import com.farhan.blog.dto.UserBlogDto;
import com.farhan.blog.dto.UserDto;
import com.farhan.blog.entity.Blog;
import com.farhan.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements  BlogService{

    @Autowired
    private BlogRepository blogRepository;



//    private boolean isLoggedIn(Integer userId){
//        return userRepository.isLoggedIn(userId);
//    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Blogdto addBlog(Blog blog) {

       Blog saved = blogRepository.save(blog);
        return new Blogdto(saved);
    }

    @Override
    public List<Blogdto> getAllBlog() {
        List<Blogdto> blogdtos = new ArrayList<>();
       blogRepository.findAll().forEach(blog -> {
           blogdtos.add(new Blogdto(blog));
       });
       return blogdtos;
    }

    @Override
    public void publishBlog(Integer blogId) {
        blogRepository.publishBlog(blogId);
    }

    @Override
    public Blogdto updateBlog(Blog blog)  {

        Blog updated = blogRepository.save(blog);
        return new Blogdto(updated);
    }

    @Override
    public List<Blogdto> getUserBlogs(Integer userId){

        List<Blogdto> blogs = new ArrayList<>();
       blogRepository.findAllByUserId(userId).forEach(blog -> blogs.add(new Blogdto(blog)));
       return blogs;

    }

    @Override
    public void deleteBlog(Integer blogId)  {
        blogRepository.deleteById(blogId);
    }

    @Override
    public UserBlogDto getBlogwithUser(Integer blogId) {
        UserBlogDto userBlogDto = new UserBlogDto();
        Blog blog = blogRepository.findByBlogId(blogId);
        UserDto user = restTemplate.getForObject("http://AUTH-SERVICE/users/"+blog.getUserId(),UserDto.class);
        userBlogDto.setBlog(new Blogdto(blog));
        userBlogDto.setUser(user);
        return userBlogDto;
    }
}
