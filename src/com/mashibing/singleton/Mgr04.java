package com.mashibing.singleton;

/**
 * @Description Mgr03
 * 饿汉式
 * 每次要锁，降低了效率
 * @Author Radish
 * @Date 2020-08-30 08:11
 */
public class Mgr04 {
    private static Mgr04 INSTANCE;

    private Mgr04() {}

    public static synchronized Mgr04 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //lambda:对一个匿名类只有一个方法的形式
            new Thread(()->{
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();
        }
    }
}
