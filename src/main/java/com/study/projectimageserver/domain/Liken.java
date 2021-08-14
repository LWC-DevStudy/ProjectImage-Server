package com.study.projectimageserver.domain;


import com.sun.tools.javac.jvm.Gen;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Liken {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long likenId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long postId;


    public Liken(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
    }
}
