package com.study.projectimageserver.repository;


import com.study.projectimageserver.domain.Liken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikenRepository extends JpaRepository<Liken, Long> {
    Liken findByPostIdAndUserId(Long postId, Long userId);
}
