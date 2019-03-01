package com.tr.BasicJava;

public class ThreadTest {
    public static void main(String[] args) {
        new ThreadTest();
    }

    public ThreadTest() {
        Sell sell = new Sell();
        new Thread(sell).start();
        new Thread(sell).start();
        new Thread(sell).start();
    }
}

class  Sell implements Runnable{
    private int ticketsNum = 10;

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("sold ticket :" + ticketsNum);
                Thread.sleep(1000);
                ticketsNum--;
                if (ticketsNum <= 0) {
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
