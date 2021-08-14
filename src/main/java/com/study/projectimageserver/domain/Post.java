package com.study.projectimageserver.domain;


import com.study.projectimageserver.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;


    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private String contents;

    @ColumnDefault("0")
    private Integer likeCnt;

    @ManyToOne
    private User user;

    public Post(PostRequestDto postRequestDto, User user) {
        this.contents = postRequestDto.getContents();
        this.imageUrl = postRequestDto.getImageUrl();
        this.user = user;
        this.likeCnt = 0;
    }

    public void edit(PostRequestDto postRequestDto) {
        this.imageUrl = postRequestDto.getImageUrl();
        this.contents = postRequestDto.getContents();
    }

    public void addlike() {
        likeCnt += 1;
    }

    public void deletelike() {
        likeCnt -= 1;
    }
}
