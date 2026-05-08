package com.chg.other;

/**
 * 单例模式，使用双重检查锁创建
 *
 */
public class Singleton {
    // volatile 可以禁止指令重排，保证线程安全
    private volatile static Singleton instance;

    // 私有化构造方法，防止外部直接 new
    private Singleton() {

    }

    // 提供全局唯一访问点，
    // 双重检查锁
    public static Singleton getInstance() {
        // 第一次检查：如果已经初始化过，则直接返回，避免不必要的同步开销
        if (instance == null) {
            // 同步代码块，保证线程安全
            synchronized (Singleton.class) {
                // 第二次检查，防止多个线程同时通过了第一次检查而导致重复创建
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
