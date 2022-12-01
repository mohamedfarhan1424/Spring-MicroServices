package com.farhan.blog.dto;



import com.farhan.blog.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blogdto {

    private Integer blogId;
    private String title;

    private String description;

    private Boolean isPublished;

    private Integer userId;

    public Blogdto(Blog blog){
        this.blogId = blog.getBlogId();
        this.title = blog.getTitle();
        this.description = blog.getDescription();
        this.isPublished = blog.getPublished();
        this.userId = blog.getUserId();
    }
}
