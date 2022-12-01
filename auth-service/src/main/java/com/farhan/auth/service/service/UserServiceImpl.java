package com.farhan.auth.service.service;



import com.farhan.auth.service.dto.UserDto;
import com.farhan.auth.service.entity.User;
import com.farhan.auth.service.error.EmailAlreadyExistsException;
import com.farhan.auth.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User createUser(User user) throws EmailAlreadyExistsException {
        try{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            userRepository.setLogin(savedUser.getEmail(),true);
            return savedUser;
        }
        catch (Exception e){

            throw new EmailAlreadyExistsException("Email Already Exists");
        }

    }

    @Override
    public void logout(String email) {
        userRepository.setLogin(email,false);
    }

    @Override
    public UserDto getUserDetails(String username) {
        User user = userRepository.findByEmail(username);
        return new UserDto(user);
    }

    @Override
    public void setLogin(String email) {
        userRepository.setLogin(email,true);
    }

    @Override
    public UserDto getUSerDetailsById(Integer userId) {
        User user = userRepository.findByUserId(userId);
        return new UserDto(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),new ArrayList<>());
    }
}
