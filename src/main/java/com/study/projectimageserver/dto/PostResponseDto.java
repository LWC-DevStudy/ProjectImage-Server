package com.study.projectimageserver.dto;


import com.study.projectimageserver.domain.Post;
import lombok.Data;


@Data
public class PostResponseDto {

    private Long postId;
    private String imageUrl;
    private String contents;
    private String username;

    public PostResponseDto(Post post) {
        this.postId = post.getPostId();
        this.imageUrl = post.getImageUrl();
        this.contents = post.getContents();
        this.username = post.getUser().getUsername();
    }
}