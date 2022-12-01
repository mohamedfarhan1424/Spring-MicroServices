package com.farhan.auth.service.dto;


import com.farhan.auth.service.entity.User;
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
    private String mobileNumber;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.mobileNumber = user.getMobileNumber();
    }

}
