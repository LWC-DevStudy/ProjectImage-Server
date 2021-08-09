package com.study.projectimageserver.domain;


import com.study.projectimageserver.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;


    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    private User user;

    public Post(PostRequestDto postRequestDto, User user) {
        this.contents = postRequestDto.getContents();
        this.imageUrl = postRequestDto.getImageUrl();
        this.user = user;
    }

    public void edit(PostRequestDto postRequestDto) {
        this.imageUrl = postRequestDto.getImageUrl();
        this.contents = postRequestDto.getContents();
    }
}
