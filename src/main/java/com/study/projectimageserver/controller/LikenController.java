package com.study.projectimageserver.controller;


import com.study.projectimageserver.security.UserDetailsImpl;
import com.study.projectimageserver.service.LikenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikenController {

    private final LikenService likenService;

    @PostMapping("/post/like/{postId}")
    public void addlike(@PathVariable(name = "postId") Long postId,
                        @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails != null){
            likenService.addLike(postId, userDetails.getUser());
        }else{
            throw new IllegalArgumentException("로그인이 필요한 서비스입니다.");
        }

    }
}
