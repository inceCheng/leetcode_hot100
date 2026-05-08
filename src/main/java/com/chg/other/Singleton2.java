package com.chg.other;

/**
 * 单例模式，内部静态类
 */
public class Singleton2 {
    private Singleton2() {

    }

    private static class Holder {
        private static final Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return Holder.INSTANCE;
    }

}
