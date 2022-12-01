package com.farhan.user.service;


import com.farhan.user.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService  {

    @Override
    public String loadUserByUsername(String username,String token) {
       UserDto user = null;
        try {
            URL url = new URL("http://localhost:9003/users/findByEmail/" + username);
            HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
            httpUrlConnection.setRequestMethod("GET");
            httpUrlConnection.setDoOutput(true);
            httpUrlConnection.setRequestProperty("Content-Type", "application/json");
            httpUrlConnection.setRequestProperty("Authorization", "Bearer " + token);

            BufferedReader br = null;
            if (100 <= httpUrlConnection.getResponseCode() && httpUrlConnection.getResponseCode() <= 399) {
                br = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(httpUrlConnection.getErrorStream()));
            }
            String json = br.readLine();
            ObjectMapper mapper = new ObjectMapper();
            user = mapper.readValue(json, UserDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return user.getEmail();

    }
}
