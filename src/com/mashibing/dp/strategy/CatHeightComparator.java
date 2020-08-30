package com.mashibing.dp.strategy;

/**
 * @Description CatHeightComparator
 * @Author Radish
 * @Date 2020-08-30 09:38
 */
public class CatHeightComparator implements Comparator<Cat> {
    @Override
    public int compare(Cat o1, Cat o2) {
        if (o1.height < o2.height) return -1;
        else if (o1.height > o2.height) return 1;
        else return 0;
    }
}
