package com.farhan.auth.service.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Users",uniqueConstraints = @UniqueConstraint(name = "emailId_unique",columnNames = "email_address"))
public class User {

    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    private Integer userId;
    private String name;
    @Column(name = "email_address", nullable = false)
    private String email;
    private String password;

    private String mobileNumber;

    @Column(columnDefinition = "tinyint(1) default false")
    private Boolean isLogin=false;

}