package com.mashibing.strategy;

/**
 * @Description CatComparator
 * @Author Radish
 * @Date 2020-08-30 09:36
 */
public class CatComparator implements Comparator<Cat> {

    @Override
    public int compare(Cat o1, Cat o2) {
        if (o1.weight < o2.weight) return -1;
        else if (o1.weight > o2.weight) return 1;
        else return 0;
    }
}
