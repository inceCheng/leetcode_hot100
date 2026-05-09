package com.chg.other;

public class RunnableTask implements Runnable {
    @Override
    public void run() {
        System.out.println("run... ... ");
    }

    public static void main(String[] args) {
        RunnableTask runnableTask = new RunnableTask();
        Thread thread = new Thread(runnableTask);
        thread.start();
    }
}
