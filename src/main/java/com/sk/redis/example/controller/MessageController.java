package com.sk.redis.example.controller;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/msg")
public class MessageController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private ActiveMQQueue queue = new ActiveMQQueue("active.queue");

    private ActiveMQTopic topic = new ActiveMQTopic("active.topic");

    /**
     * 发送订阅消息，保持在active.topic中
     * @param msg
     */
    @RequestMapping("/sendMsg")
    public void sendMsg(String msg){
        jmsMessagingTemplate.convertAndSend(topic, msg);
    }

    /**
     * 发送队列消息，保持在active.queue中
     * @param msg
     */
    @RequestMapping("/send")
    public void send(String msg){
        jmsMessagingTemplate.convertAndSend(queue, msg);
    }

}
