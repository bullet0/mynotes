package com.example.mongotest.dao;

import com.example.mongotest.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Map;

public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByContent(String content, Pageable pageable);
    List<Comment> findByContentLikeAndNickName(String content,String nickName, Pageable pageable);

    List<Map<String, Object>> groupById();
}
