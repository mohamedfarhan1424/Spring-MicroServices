package com.farhan.auth.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Log {

    private String user;
    private Object request;
    private Object response;
    private String operation;

    private String type;
    private String timestamp= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());




    public Log(String user, Object request, Object response, String type, String operation) {
        this.user = user;
        this.request = request;
        this.response = response;
        this.type = type.equals("RRL") ? "Request-Response-Log": "Exception Log";
        this.operation = operation;
    }



}
