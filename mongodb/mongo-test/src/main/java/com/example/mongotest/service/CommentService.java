package com.example.mongotest.service;

import com.example.mongotest.dao.CommentRepository;
import com.example.mongotest.entity.Comment;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.AggregationSpELExpression;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.beans.Transient;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @program: com.example.mongotest.service.CommentService
 * @description:
 * @author: Mr.BULLET
 * @create: 2020-03-17 15:26
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public void findList(){
        List<Comment> all = commentRepository.findAll();
        System.out.println(all);
    }
    public void findByContent(String content, Pageable pageable){
        List<Comment> all = commentRepository.findByContent(content,pageable);
        System.out.println(all);
    }
    public void findLikeContent(String content, Pageable pageable){
        List<Comment> all = commentRepository.findByContentLikeAndNickName(content,"2",pageable);
        System.out.println(all);
    }
    public void findOne(String id){
        Optional<Comment> byId = commentRepository.findById(id);
        System.out.println(byId.get().getCreatedatetime().toString());
    }
    public void count(){
        long count = commentRepository.count();
        System.out.println(count);
    }
    public void insertOne(Comment comment){
        Comment save = commentRepository.save(comment);
        System.out.println(save);
    }
    public void insertAll(List<Comment> comments){
        StopWatch sw = new StopWatch();
        sw.start();
        List<Comment> comments1 = commentRepository.saveAll(comments);
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println(comments1);
    }
    @Transactional
    public void updateById(String id){
        Query query = Query.query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("likenum",1);
        mongoTemplate.updateFirst(query,update,Comment.class);
    }
    @Transactional
    public void removeById(String id){
        Query query = Query.query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query,Comment.class);
    }

    public List<HashMap> customerDetailList(){
//        Criteria criteria = Criteria.where("userId").is("");
        Aggregation customerAgg = Aggregation.newAggregation(
//                Aggregation.project("buyerNick","payment","num","tid","userId","address","mobile","orders"),
//                Aggregation.match(criteria),Aggregation.unwind("orders"),
                Aggregation.group("articleId").sum((v)->{
                    return new Document("$sum", 1);
                }).as("count")
//                Aggregation.sort(new Sort(new Sort.Order(Sort.Direction.DESC, "totalPayment"))),
//                Aggregation.skip(5L),
//                Aggregation.limit(10)
        );
        AggregationResults<HashMap> comment = mongoTemplate.aggregate(customerAgg, "comment", HashMap.class);

        List<HashMap> mappedResults = comment.getMappedResults();
        System.out.println(mappedResults);
        return mappedResults;
    }
    public List<HashMap> customerMapReduceList(){
        Query query = new Query();//直接new  相当于传全查 query:{}   也可以下面调用mapReduce不传query
        String map = "function(){\n" +
                "    if(this.name != null){\n" +
                "      emit(this.name,this);\n" +
                "    }\n" +
                "  }";
        String reduce = "function(k,values){\n" +
                "    print(k);\n" +
                "    var str = \"\";\n" +
                "    for(var i = 0 ;i<values.length;i++){\n" +
                "      str+=values[i];\n" +
                "    }\n" +
                "    return str;\n" +
                "  }";
        // query 查询条件
        // comment 从哪个库查
        // map function
        // reduce function
        // options
        // 返回值泛型类型
        // 这里返回值就是最终落入aaa表中的数据结构,可以自己建一个,也可以用HashMap简单
        MapReduceResults<HashMap> aaa = mongoTemplate.mapReduce(query,"comment", map, reduce,
                MapReduceOptions.options().outputCollection("aaa"),HashMap.class);
        System.out.println(aaa.iterator().next());
        return null;
    }
}
