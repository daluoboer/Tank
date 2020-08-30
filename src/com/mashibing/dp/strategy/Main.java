package com.mashibing.dp.strategy;

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
        Dog[] d = {new Dog(3),new Dog(5),new Dog(1)};
        Sortor<Cat> sortor = new Sortor();
        sortor.sort(a,new CatHeightComparator());
        //咦，这个函数式表达式咋获取不到参数的属性呢,a~因为sortor的泛型你没有填进去
        sortor.sort(a,(o1,o2)->{
            if (o1.height < o2.height) return -1;
            else if (o1.height > o2.height) return 1;
            else return 0;
        });
        System.out.println(Arrays.toString(a));
    }
}
