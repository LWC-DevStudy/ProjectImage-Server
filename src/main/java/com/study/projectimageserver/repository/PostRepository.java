package com.study.projectimageserver.repository;


import com.study.projectimageserver.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderByCreatedAtDesc();
}