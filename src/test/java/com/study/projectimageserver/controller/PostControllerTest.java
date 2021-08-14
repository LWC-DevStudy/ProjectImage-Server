package com.study.projectimageserver.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PostControllerTest {

    @Autowired PostController postController;

    @Test
    @DisplayName("mmmmmmm")
    void test(){

        assertNotNull(postController);

    }
}

