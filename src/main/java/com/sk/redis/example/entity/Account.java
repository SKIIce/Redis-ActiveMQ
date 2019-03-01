package com.sk.redis.example.entity;

/**
 * 验证多线程同步机制，根据status为不同线程去执行相应的任务
 */
public class Account {
    private int status = 1;
    public Account(){}

    public int getStatus(){
        System.out.println("当前 status = " + status);
        return status;
    }

    public void A(){
        synchronized (this){
            if(status != 1){
                try{
                    System.out.println("准备A任务...");
                    getStatus();
                    this.wait();
                }catch (Exception e){}
            }else{
                System.out.println("当前正在执行A任务...");
                try{
                    getStatus();
                    Thread.sleep(300);
                }catch (Exception e){}
                System.out.println("A任务执行完毕.");
                status = 2;
                this.notifyAll();
            }
        }
    }

    public void B(){
        synchronized (this){
            if(status != 2){
                try{
                    System.out.println("准备B任务...");
                    getStatus();
                    this.wait();
                }catch (Exception e){}
            }else{
                System.out.println("当前正在执行B任务...");
                try{
                    getStatus();
                    Thread.sleep(300);
                }catch (Exception e){}
                System.out.println("B任务执行完毕.");
                status = 3;
                this.notifyAll();
            }
        }
    }

    public void C(){
        synchronized (this){
            if(status != 3){
                try{
                    System.out.println("准备C任务...");
                    getStatus();
                    this.wait();
                }catch (Exception e){}
            }else{
                System.out.println("当前正在执行C任务...");
                try{
                    getStatus();
                    Thread.sleep(300);
                }catch (Exception e){}
                System.out.println("C任务执行完毕.");
                status = 1;
                this.notifyAll();
            }
        }
    }

    public static void main(String[] args){
        Account account = new Account();
        Thread t1 = new Thread(new AccountHandlerA(account));
        Thread t2 = new Thread(new AccountHandlerB(account));
        Thread t3 = new Thread(new AccountHandlerC(account));
        t1.start();
        t2.start();
        t3.start();

    }

}

/**
 * 负责A任务的执行线程
 */
class AccountHandlerA implements Runnable{
    private Account account;
    public AccountHandlerA(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                account.A();
            } catch (Exception e) {
            }
        }
    }
}

/**
 * 负责B任务的执行线程
 */
class AccountHandlerB implements Runnable{
    private Account account;
    public AccountHandlerB(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                account.B();
            } catch (Exception e) {
            }
        }
    }
}

/**
 * 负责C任务的执行线程
 */
class AccountHandlerC implements Runnable{
    private Account account;
    public AccountHandlerC(Account account){
        this.account = account;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(1000);
                account.C();
            } catch (Exception e) {
            }
        }
    }
}
