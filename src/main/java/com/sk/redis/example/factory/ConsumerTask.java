package com.sk.redis.example.factory;

public class ConsumerTask implements Runnable{
    private String threadName;
    public ConsumerTask(String name){
        this.threadName = name;
    }

    @Override
    public void run() {
        try {
            WhiseHouse.getInstance().consumer(threadName);
            Thread.sleep(1000);
        }catch (Exception e){

        }
    }

    public static void main(String[] args){
        try {
            WhiseHouse w = WhiseHouse.class.newInstance();
            w.getCapacity();
        }catch (Exception e){

        }
    }
}
