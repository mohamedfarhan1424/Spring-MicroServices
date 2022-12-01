package com.farhan.auth.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequestModel implements Serializable {
    private static final long serialVersionUID = 2636936156391265891L;
    private String email;
    private String password;
}