package com.study.projectimageserver.controller;


import com.study.projectimageserver.domain.Post;
import com.study.projectimageserver.dto.PostRequestDto;
import com.study.projectimageserver.security.UserDetailsImpl;
import com.study.projectimageserver.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public Page<Post> postPage(@RequestParam("page") int page, @RequestParam("size") int size){
        return postService.getPostPage(page,size);
    }

    @PostMapping("/post/create")
    public Long createPost(@RequestBody PostRequestDto postRequestDto,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(postRequestDto, userDetails.getUser());
    }

    @PutMapping("/post/edit/{postId}")
    public Long editPost(@PathVariable(name = "postId") Long postId,
                         @AuthenticationPrincipal UserDetailsImpl userDetails,
                         @RequestBody PostRequestDto postRequestDto){
        return postService.editPost(postId, postRequestDto, userDetails.getUser());
    }

    @DeleteMapping("/post/delete/{postId}")
    public Long deletePost(@PathVariable(name = "postId") Long postId,
                           @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.deletePost(postId, userDetails.getUser());
    }

}
