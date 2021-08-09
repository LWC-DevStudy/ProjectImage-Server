package com.study.projectimageserver.service;


import com.study.projectimageserver.domain.Post;
import com.study.projectimageserver.domain.User;
import com.study.projectimageserver.dto.PostRequestDto;
import com.study.projectimageserver.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    PostRepository postRepository;

    public Page<Post> getPostPage(int page, int size) {
        Pageable pageable = PageRequest.of(page,size);

        return postRepository.findAllByOrderByCreatedAtDesc(pageable);
    }

    public Long createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto,user);
        postRepository.save(post);
        return post.getPostId();
    }

    @Transactional
    public Long editPost(Long postId, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("No Such Post")
        );

        if(!user.equals(post.getUser())){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        post.edit(postRequestDto);

        return post.getPostId();
    }

    public Long deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new NullPointerException("NO SUCH POST")
        );
        if(!user.equals(post.getUser())){
            throw new IllegalArgumentException("권한이 없스니다.");
        }
        postRepository.deleteById(postId);
        return post.getPostId();
    }
}
