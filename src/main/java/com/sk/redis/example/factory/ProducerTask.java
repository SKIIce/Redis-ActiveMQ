package com.sk.redis.example.factory;

public class ProducerTask implements Runnable{
    private String threadName;
    public ProducerTask(String name){
        this.threadName = name;
    }

    @Override
    public void run() {
        try {
            WhiseHouse.getInstance().producer(threadName);
            Thread.sleep(10);
        }catch (Exception e){

        }
    }

    public static void main(String[] args){
        for(int i = 0; i < 30; i++){
            new Thread(new ProducerTask("生产线程" + i)).start();
        }
    }
}
