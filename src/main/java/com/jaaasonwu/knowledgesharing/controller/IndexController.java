package com.jaaasonwu.knowledgesharing.controller;

import com.jaaasonwu.knowledgesharing.model.User;
import com.jaaasonwu.knowledgesharing.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    //    @RequestMapping(path = {"/", "/index"})
//    @ResponseBody
//    public String index() {
//        return "Hello world!";
//    }

    @Autowired
    QuestionService questionService;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(path = {"/profile/{userId}"})
    @ResponseBody
    public String profile(@PathVariable("userId") int userId,
                          @RequestParam(value = "type", defaultValue = "1", required = false) Integer type) {
        return String.format("Profile page of %d, t: %d", userId, type) + questionService.getMessage(userId);
    }

    @RequestMapping(path = {"/"})
    public String index(Model model, HttpSession session) {
        logger.info("After before");
        model.addAttribute("value1", "test");
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            map.put(i, i * i);
        }
        model.addAttribute("map", map);

        User user = new User("Jason");
        model.addAttribute("user", user);
        model.addAttribute("msg", session.getAttribute("msg"));
        return "index";
    }

    @RequestMapping(path = "/request")
    @ResponseBody
    public String request(Model model, HttpServletRequest req, HttpServletResponse res, HttpSession session) {
        StringBuilder sb = new StringBuilder();
        sb.append(req.getMethod() + "<br>");
        sb.append(req.getQueryString() + "<br>");
        sb.append(req.getPathInfo() + "<br>");
        sb.append(req.getRequestURI() + "<br>");

        res.addCookie(new Cookie("username", "jaaasonwu"));
        return sb.toString();
    }

    @RequestMapping(path = "/redirect/{code}")
    public RedirectView redirect(@PathVariable("code") int code, HttpSession session) {
        session.setAttribute("msg", "Jump from redirect");
        RedirectView red = new RedirectView("/", true);
        if (code == 1) {
            red.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }

        return red;
    }

    @RequestMapping(path={"/admin"})
    @ResponseBody
    public String admin(@RequestParam("key") String key) {
        if ("admin".equals(key)) {
            return "Hello Admin";
        }
        throw new IllegalArgumentException("Incorrect Arguments");
    }

    @ExceptionHandler
    @ResponseBody
    public String error (Exception e) {
        return "error:" + e.getMessage();
    }
}
