package com.jaaasonwu.knowledgesharing.dao;

import com.jaaasonwu.knowledgesharing.model.Question;
import com.jaaasonwu.knowledgesharing.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = " question ";
    String INSERT_FIELDS = " title, content, create_date, user_id, comment_count";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"INSERT INTO ",TABLE_NAME, "(",INSERT_FIELDS,") VALUES (#{title}, #{content}, #{createDate}, #{userId}, #{commentCount})"})
    int addQuestion(Question question);

    List<Question> selectLatestQuestions(@Param("userId") int userId,
                                         @Param("offset") int offset,
                                         @Param("limit") int limit);

}
