package com.mashibing.singleton;

/**
 * @Description Mgr01
 * 饿汉式
 *
 * 推荐！只有一个缺点，就是没用的时候就加载了
 * @Author Radish
 * @Date 2020-08-30 08:10
 */
public class Mgr01 {
    private final static Mgr01 INSTANCE = new Mgr01();

    private Mgr01() {
    }

    public static Mgr01 getInstance() {
        return INSTANCE;
    }
}
