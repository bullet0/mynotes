package com.example.mongotest.service;

import com.example.mongotest.entity.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    CommentService commentService;
    @Test
    public void findList() {
        commentService.findList();
    }

    @Test
    public void count() {
        commentService.count();
    }

    @Test
    public void insertOne() {
        Comment comment = new Comment();
        comment.setArticleId("2222");
        comment.setCreatedatetime(LocalDateTime.now());
        commentService.insertOne(comment);
    }

    @Test
    public void insertAll() {
        List<Comment> list = new ArrayList<>();
        Comment comment1 = new Comment();
        comment1.setArticleId("1111");
        comment1.setLikenum(10);
        comment1.setCreatedatetime(LocalDateTime.now());
        comment1.setContent("内容11111");
        list.add(comment1);
        Comment comment2 = new Comment();
        comment2.setArticleId("1111");
        comment2.setLikenum(10);
        comment2.setCreatedatetime(LocalDateTime.now());
        comment2.setContent("内容11111");
        list.add(comment2);
        Comment comment3 = new Comment();
        comment3.setArticleId("1111");
        comment3.setCreatedatetime(LocalDateTime.now());
        comment3.setLikenum(10);
        comment3.setContent("内容11111");
        list.add(comment3);
        Comment comment4 = new Comment();
        comment4.setArticleId("1111");
        comment4.setCreatedatetime(LocalDateTime.now());
        comment4.setLikenum(10);
        comment4.setContent("内容11111");
        list.add(comment4);
        commentService.insertAll(list);
    }

    @Test
    public void findOne() {
        commentService.findOne("5e707fd681d4260c34adecca");
    }

    @Test
    public void findByContent() {
        commentService.findByContent("内容11111", PageRequest.of(0,2));
    }

    @Test
    public void findLikeContent() {
        commentService.findLikeContent("111", PageRequest.of(0,2));
    }

    @Test
    public void updateById() {
        commentService.updateById("5e70606f8d4c7154cb761f99");
    }

    @Test
    public void removeById() {
        commentService.removeById("5e7197add9d5b4b13eb50a7d");
    }

    @Test
    public void customerDetailList() {
        List<HashMap> comments = commentService.customerDetailList();
    }

    @Test
    public void customerMapReduceList() {
        commentService.customerMapReduceList();
    }

    @Test
    public void customerGroupByList2() {
        commentService.customerGroupByList2();
    }
}
