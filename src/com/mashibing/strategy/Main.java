package com.mashibing.strategy;

import java.util.Arrays;

/**
 * @Description Main
 * @Author Radish
 * @Date 2020-08-30 08:49
 */
public class Main {
    public static void main(String[] args) {
//        int[] ints = {1, 3, 2, 6, 9, 11, 0};

        Cat[] a = {new Cat(3,3), new Cat(5,1),new Cat(1,5)};
//        Dog[] a = {new Dog(3),new Dog(5),new Dog(1)};
        Sortor sortor = new Sortor();
        sortor.sort(a,new CatHeightComparator());
        System.out.println(Arrays.toString(a));
    }
}
