package com.sk.redis.example.decorate;

public class Cloth extends Decorate{

    public Cloth(Person p) {
        super(p);
    }

    @Override
    public String getName() {
        return "穿着校服的" + p.getName();
    }

    @Override
    public void desc() {
        p.desc();
        System.out.println("穿上衣服...");
    }
}
