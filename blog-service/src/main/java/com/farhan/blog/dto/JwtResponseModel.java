package com.farhan.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String token;

}
