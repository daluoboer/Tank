package com.mashibing.singleton;

/**
 * @Description Mgr03
 * 饿汉式
 * double-check
 * @Author Radish
 * @Date 2020-08-30 08:11
 */
public class Mgr06 {
    private volatile static Mgr06 INSTANCE;//为啥要加volatile 指令重排序，可能会返回一个未初始化完成的对象还是啥，请参见JVM

    private Mgr06() {}

    public static Mgr06 getInstance() {
        if (INSTANCE == null) {
            synchronized (Mgr06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //lambda:对一个匿名类只有一个方法的形式
            new Thread(()->{
                System.out.println(Mgr06.getInstance().hashCode());
            }).start();
        }
    }
}
