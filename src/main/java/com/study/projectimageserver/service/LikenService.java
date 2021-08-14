package com.study.projectimageserver.service;


import com.study.projectimageserver.domain.Liken;
import com.study.projectimageserver.domain.Post;
import com.study.projectimageserver.domain.User;
import com.study.projectimageserver.repository.LikenRepository;
import com.study.projectimageserver.repository.PostRepository;
import com.study.projectimageserver.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class LikenService {

    private final LikenRepository likenRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addLike(Long postId, User user) {
        Liken liken1 = likenRepository.findByPostIdAndUserId(postId, user.getUserId());
        if(liken1 == null){
            Post post = postRepository.findById(postId).orElseThrow(
                    ()-> new NullPointerException("해당 포스트가 없습니다.")
            );
            post.addlike();
            Liken liken2 = new Liken(postId,user.getUserId());
            likenRepository.save(liken2);
        }else {
            Post post = postRepository.findById(postId).orElseThrow(
                    ()-> new NullPointerException("해당 포스트가 없습니다.")
            );
            post.deletelike();
            likenRepository.delete(liken1);
        }

    }
}
