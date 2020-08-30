package com.mashibing.strategy;

/**
 * @Description Comparator
 * @Author Radish
 * @Date 2020-08-30 09:25
 */
public interface Comparator<T> {
    int compare(T o1, T o2);
    default void m() {
        System.out.println("m");
    }
}
