package com.sk.redis.example.decorate;

public class Shoe extends Decorate{

    public Shoe(Person p){
        super(p);
    }

    @Override
    public String getName() {
        return "穿着运动鞋的" + p.getName();
    }

    @Override
    public void desc() {
        p.desc();
        System.out.println("穿上鞋子...");
    }
}
