package com.mashibing.singleton;

/**
 * @Description Mgr03
 * 静态内部类
 * JVM保证线程安全
 * 完美写法之一
 * @Author Radish
 * @Date 2020-08-30 08:11
 */
public class Mgr07 {
    private Mgr07() {}

    private static class Mgr07Holder {
        private final static Mgr07 INSTANCE = new Mgr07();
    }

    public static Mgr07 getInstance() {
        return Mgr07Holder.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //lambda:对一个匿名类只有一个方法的形式
            new Thread(()->{
                System.out.println(Mgr07.getInstance().hashCode());
            }).start();
        }
    }
}
