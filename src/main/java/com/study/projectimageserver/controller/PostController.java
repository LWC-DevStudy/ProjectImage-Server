package com.study.projectimageserver.controller;


import com.study.projectimageserver.dto.PostRequestDto;
import com.study.projectimageserver.dto.PostResponseDto;
import com.study.projectimageserver.security.UserDetailsImpl;
import com.study.projectimageserver.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public List<PostResponseDto> postPage(@AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails!=null){
            return postService.getPostLogin(userDetails.getUser());
        }else {
            return postService.getPostPage();
        }

    }

    @GetMapping("/post/{postId}")
    public PostResponseDto postDetailPage(@PathVariable(name = "postId") Long id){
        return postService.getPostDetail(id);
    }

    @PostMapping("/post/create")
    public Long createPost(@RequestBody PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            throw new IllegalArgumentException("로그인 정보가 없습니다.");
        }
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    @PutMapping("/post/edit/{postId}")
    public Long editPost(@PathVariable(name = "postId") Long postId,
                         @AuthenticationPrincipal UserDetailsImpl userDetails,
                         @RequestBody PostRequestDto postRequestDto){
        if(userDetails == null){
            throw new IllegalArgumentException("로그인 정보가 없습니다.");
        }
        return postService.editPost(postId, postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/post/delete/{postId}")
    public Long deletePost(@PathVariable(name = "postId") Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails == null){
            throw new IllegalArgumentException("로그인 정보가 없습니다.");
        }
        return postService.deletePost(postId, userDetails.getUser());
    }

}
