package com.sk.redis.example.controller;

import com.sk.redis.example.entity.User;
import com.sk.redis.example.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/test")
public class WebController {
    @Autowired
    private RedisService redisService;

    @RequestMapping("/session")
    public @ResponseBody String getSeesion(HttpServletRequest request){
        HttpSession session = request.getSession();
        ServletContext servletContext = request.getServletContext();
        if(servletContext.getAttribute("system")== null) {
            servletContext.setAttribute("system", "user");
        }
        session.setMaxInactiveInterval(30);
        return session.getId();
    }


    @RequestMapping("/context")
    public @ResponseBody String getContext(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        return (String) servletContext.getAttribute("system");
    }

    @GetMapping("/w")
    public String setString(){
        redisService.set("redis_string_test", "springboot redis test");
        return "success";
    }
}
