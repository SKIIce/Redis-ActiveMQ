package com.sk.redis.example.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class User implements Serializable{
    private static final long serialVersionUID = -1L;

    private String name;
    private Integer age;
    private String phone;

    public String toString(){
        return "user:{" + this.name + "," + this.age + "," + this.phone + "}";
    }

    public int add(int a, int b){
        return a + b;
    }
}
