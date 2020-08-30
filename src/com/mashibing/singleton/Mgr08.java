package com.mashibing.singleton;

/**
 * @Description Mgr08
 * 不仅可以解决线程同步，还可以反序列化
 * 前面的方法都可以被反序列化，可以通过class文件获取instance
 * 枚举类没有构造方法，从这个角度说就只有它是最完美的写法（它是abstractClass）
 * 完美写法之一
 * @Author Radish
 * @Date 2020-08-30 08:26
 */
public enum Mgr08 {
    INSTANCE;

    public void m() {}

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //lambda:对一个匿名类只有一个方法的形式
            new Thread(()->{
                System.out.println(Mgr07.getInstance().hashCode());
            }).start();
        }
    }
}
