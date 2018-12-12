package com.jaaasonwu.knowledgesharing.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    @Before("execution(* com.jaaasonwu.knowledgesharing.controller.IndexController.*(..))")
    public void before() {
        logger.info("Before method" + new Date());
    }

    @After("execution(* com.jaaasonwu.knowledgesharing.controller.IndexController.*(..))")
    public void after() {
        logger.info("After method" + new Date());
    }
}
