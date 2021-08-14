package com.study.projectimageserver.service;


import com.study.projectimageserver.domain.Liken;
import com.study.projectimageserver.domain.Post;
import com.study.projectimageserver.domain.User;
import com.study.projectimageserver.dto.PostRequestDto;
import com.study.projectimageserver.dto.PostResponseDto;
import com.study.projectimageserver.repository.LikenRepository;
import com.study.projectimageserver.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final LikenRepository likenRepository;

    public List<PostResponseDto> getPostPage() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtos.add(postResponseDto);
        }
        return postResponseDtos;
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

    public List<PostResponseDto> getPostLogin(User user) {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            if(likenRepository.findByPostIdAndUserId(post.getPostId(), user.getUserId()) != null){
                PostResponseDto postResponseDto = new PostResponseDto(post);
                postResponseDto.setUserLike(true);
                postResponseDtos.add(postResponseDto);
            }else{
                PostResponseDto postResponseDto = new PostResponseDto(post);
                postResponseDtos.add(postResponseDto);
            }

        }
        return postResponseDtos;
    }
}
