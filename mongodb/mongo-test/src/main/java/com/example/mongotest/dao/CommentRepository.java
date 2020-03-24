package com.example.mongotest.dao;

import com.example.mongotest.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment,String> {
    List<Comment> findByContent(String content, Pageable pageable);
    List<Comment> findByContentLikeAndNickName(String content,String nickName, Pageable pageable);

}
