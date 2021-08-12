package com.study.projectimageserver.dto;


import lombok.Data;

@Data
public class PostRequestDto {

    private String imageUrl;
    private String contents;
    private String username;

}
