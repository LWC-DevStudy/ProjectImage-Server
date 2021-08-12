package com.study.projectimageserver.dto;


import lombok.Data;

@Data
public class TokenResponseDto {

    private String token;
    private Long userId;
    private String username;
}
