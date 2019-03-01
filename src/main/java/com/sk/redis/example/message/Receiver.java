package com.sk.redis.example.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @JmsListener(destination = "my-destination")
    public void receivedMessage(String message){
        System.out.println("接受到" + message);
    }

    @JmsListener(destination = "active.queue")
    public void receivedQueueMessage(String message){
        System.out.println("接受到队列消息：" + message);
    }

    @JmsListener(destination = "active.topic")
    public void receivedTopicMessage(String message){
        System.out.println("接受到订阅消息：" + message);
    }
}
