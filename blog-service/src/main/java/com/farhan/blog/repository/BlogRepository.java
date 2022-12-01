package com.farhan.blog.repository;

import com.farhan.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    @Query(value= "Update blogs set published = true where blog_id = :blogId",nativeQuery = true)
    @Modifying
    @Transactional
    public void publishBlog(@Param("blogId") Integer blogId);

    @Query(value = "Select * from blogs where user_id = :userId",nativeQuery = true)
    public List<Blog> findAllByUserId(@Param("userId") Integer userId);

    Blog findByBlogId(Integer blogId);
}
