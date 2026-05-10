package com.chg.other;

public class ThreadTask extends Thread {
    @Override
    public void run() {
        System.out.println("重写 run 方法");
    }

    public static void main(String[] args) {
        ThreadTask threadTask = new ThreadTask();
        threadTask.start();
    }
}
