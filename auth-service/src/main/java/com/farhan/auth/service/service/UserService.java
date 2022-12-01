package com.farhan.auth.service.service;

import com.farhan.auth.service.dto.UserDto;
import com.farhan.auth.service.entity.User;
import com.farhan.auth.service.error.EmailAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public User createUser(User user) throws EmailAlreadyExistsException;

    public void logout(String email);

    public UserDto getUserDetails(String username);

    public void setLogin(String email);

    UserDto getUSerDetailsById(Integer userId);

    User findByEmail(String email);
}
