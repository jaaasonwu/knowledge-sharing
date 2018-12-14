package com.jaaasonwu.knowledgesharing.dao;

import com.jaaasonwu.knowledgesharing.KnowledgeSharingApplication;
import com.jaaasonwu.knowledgesharing.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KnowledgeSharingApplication.class)
@SpringBootTest
public class QuestionDAOTest {

    @Autowired
    QuestionDAO questionDAO;

    @Test
    public void addQuestionTest() {
        for (int i = 0; i < 5; i++) {
            Question question = new Question();
            question.setTitle("Question" + i);
            question.setContent("This is question " + i);
            Date date = new Date();
            date.setTime(date.getTime() + 1000 * 3600 * i);
            question.setCreateDate(date);
            question.setCommentCount(0);
            question.setUserId(1);

            questionDAO.addQuestion(question);
        }

        assert (questionDAO.selectLatestQuestions(1, 0, 1).get(0) != null);
    }

}