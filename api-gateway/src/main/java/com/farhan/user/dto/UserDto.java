package com.farhan.user.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer userId;
    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private Boolean isLogin;


}
