package com.farhan.auth.service.controller;


import com.farhan.auth.service.dto.JwtRequestModel;
import com.farhan.auth.service.dto.JwtResponseModel;
import com.farhan.auth.service.dto.UserDto;
import com.farhan.auth.service.entity.User;
import com.farhan.auth.service.error.EmailAlreadyExistsException;
import com.farhan.auth.service.jwtutils.TokenManager;
import com.farhan.auth.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;


    @PostMapping("/register")
    public ResponseEntity<Object> createUser(@RequestBody User user) throws EmailAlreadyExistsException {
        User savedUser = null;
        try{
        savedUser = userService.createUser(user);
        final UserDetails userDetails = userService.loadUserByUsername(savedUser.getEmail());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        JwtResponseModel response = new JwtResponseModel(jwtToken);
        return ResponseEntity.ok(response);
        }
        catch (EmailAlreadyExistsException e) {
            throw e;
        }

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody JwtRequestModel request) throws Exception {

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        }
        catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        final UserDetails userDetails = userService.loadUserByUsername(request.getEmail());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        userService.setLogin(request.getEmail());

        JwtResponseModel response = new JwtResponseModel(jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUserDetails")
    public ResponseEntity<UserDto> userDetails(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(username);
        UserDto user = userService.getUserDetails(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}")
    public UserDto userDetailsUsingId(@PathVariable Integer userId){
        UserDto userDto = userService.getUSerDetailsById(userId);
        return userDto;
    }

    @GetMapping("/findByEmail/{email}")
    public User findByEmail(@PathVariable String email){
       User user =userService.findByEmail(email);
       System.out.println(user);
       return user;
    }

}
