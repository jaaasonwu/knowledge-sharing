package com.jaaasonwu.knowledgesharing.service;

import com.jaaasonwu.knowledgesharing.dao.UserDAO;
import com.jaaasonwu.knowledgesharing.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void initDB() {
        Random random = new Random();
        for (int i = 0; i < 11; i++) {
            User user = new User();
            user.setHead_url(String.format("http://images.nowcode.com/head/%dt.png", random.nextInt(1000)));
            user.setName("User" + i);
            user.setPassword("");
            user.setSalt("");
            userDAO.addUser(user);

            user.setPassword("123");
            userDAO.updatePassword(user);

        }
        User user = userDAO.selectByName("User0");
        logger.info(user.getPassword());
        userDAO.deleteById(user.getId());
    }
}
