package com.farhan.blog.controller;


import com.farhan.blog.dto.Blogdto;
import com.farhan.blog.dto.UserBlogDto;
import com.farhan.blog.entity.Blog;
import com.farhan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;


    @PostMapping("/addblog")
    public Blogdto addBlog(@RequestBody Blog blog){
        Blogdto response = blogService.addBlog(blog);
        return response;
    }

    @GetMapping("/allBlogs")
    public List<Blogdto> getAllBlog(HttpServletRequest request){
        System.out.println(request.getHeader("User-Agent"));
       List<Blogdto> blogs = blogService.getAllBlog();
       return blogs;
    }

    @PostMapping("/publishblog/{blogId}")
    public String publishBlog(@PathVariable String blogId) {
        blogService.publishBlog(Integer.parseInt(blogId));
        return "Published successfully";
    }

    @GetMapping("/userblogs/{userId}")
    public List<Blogdto> getUserBlogs(@PathVariable String userId) {
        List<Blogdto> blogs = blogService.getUserBlogs(Integer.parseInt(userId));
        return blogs;
    }

    @GetMapping("/blogwithuser/{blogId}")
    public UserBlogDto blogWithUser(@PathVariable Integer blogId){
        return blogService.getBlogwithUser(blogId);
    }

    @PutMapping("/updateblog")
    public Blogdto updateBlog(@RequestBody Blog blog) {
        Blogdto updated = blogService.updateBlog(blog);
        return updated;
    }



    @DeleteMapping("/deleteblog/{blogId}")
    public String deleteBlog(@PathVariable String blogId)  {
        blogService.deleteBlog(Integer.parseInt(blogId));
        return "Deleted successfully";
    }
}
