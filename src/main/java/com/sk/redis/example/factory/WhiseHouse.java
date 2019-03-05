package com.sk.redis.example.factory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WhiseHouse {
    private static volatile int capacity = 0;
    private final static WhiseHouse instance = new WhiseHouse();

    public static WhiseHouse getInstance() {
        return instance;
    }

    public int getCapacity(){
        return capacity;
    }

    public synchronized void producer(String name){
        try{
            while(capacity == 10){
                this.wait();
            }
            capacity ++;
            System.out.println(name + " 库存：" +  WhiseHouse.getInstance().getCapacity());
            Thread.sleep(10);
            this.notifyAll();
        }catch (Exception ex){

        }
    }

    public synchronized void consumer(String name){
        try{
            while(capacity == 0){
                wait();
            }
            capacity --;
            System.out.println(name + " 库存：" +  WhiseHouse.getInstance().getCapacity());
            Thread.sleep(10);
            this.notifyAll();
        }catch (Exception ex){

        }
    }

    public static void main(String[] args){
        ExecutorService pool = Executors.newFixedThreadPool(500);
        for(int i = 0; i < 30; i++){
            pool.submit(new ProducerTask("生产线程" + i));
            pool.submit(new ConsumerTask("消费线程" + i));
//            new Thread(new ProducerTask("生产线程" + i)).start();
//            new Thread(new ConsumerTask("消费线程" + i)).start();
        }
    }
}
