package com.jaaasonwu.knowledgesharing.service;

import org.springframework.stereotype.Service;

@Service
public class QuestionService {
    public String getMessage(int userId) {
        return "Hello Message:" + userId;
    }
}
