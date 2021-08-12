package com.study.projectimageserver.controller;

import com.study.projectimageserver.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PostControllerTest {

    @Autowired PostController postController;

    @Test
    void test(){

        assertNotNull(postController);

    }
}