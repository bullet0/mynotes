package com.example.mongotest.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @program: com.example.mongotest.entity.Comment
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-17 14:24
 */
@Document("comment")
@Data
public class Comment {
    @Id
    private String id;
    @Field("articleId")
    private String articleId;
    @Field("content")
    private String content;
    @Field("createdatetime")
    private LocalDateTime createdatetime;
    @Field("likenum")
    private Integer likenum;
    @Field("nickName")
    private String nickName;
    @Field("parentId")
    private String parentId;
    @Field("repaynum")
    private String repaynum;
    @Field("state")
    private String state;
    @Field("userId")
    private String userId;
}
