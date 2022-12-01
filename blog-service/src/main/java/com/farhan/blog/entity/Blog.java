package com.farhan.blog.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Blogs")
public class Blog {

    @Id
    @SequenceGenerator(name = "blog_sequence", sequenceName = "blog_sequence" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "blog_sequence")
    private Integer blogId;
    private String title;
    @Column(columnDefinition = "varchar(255)")
    private String description;
    @Column(columnDefinition = "tinyint(1) default false")
    private Boolean published=false;

    private Integer userId;
}
