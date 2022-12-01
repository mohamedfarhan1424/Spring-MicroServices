package com.farhan.user.service;

import com.farhan.user.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface UserService  {
    public String loadUserByUsername(String username,String token);

}
