package com.mashibing.singleton;

/**
 * @Description Mgr03
 * 饿汉式
 * 线程不安全
 * @Author Radish
 * @Date 2020-08-30 08:11
 */
public class Mgr03 {
    private static Mgr03 INSTANCE;

    private Mgr03() {}

    public static Mgr03 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //lambda:对一个匿名类只有一个方法的形式
            new Thread(()->{
                System.out.println(Mgr03.getInstance().hashCode());
            }).start();
        }
    }
}
