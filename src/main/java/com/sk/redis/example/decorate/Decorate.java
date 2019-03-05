package com.sk.redis.example.decorate;

public abstract class Decorate extends Person{
    protected Person p;

    public  Decorate(Person p){
        this.p = p;
    }
}
