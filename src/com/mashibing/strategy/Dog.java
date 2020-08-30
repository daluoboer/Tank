package com.mashibing.strategy;

/**
 * @Description Dog
 * @Author Radish
 * @Date 2020-08-30 09:20
 */
public class Dog implements Comparable<Dog>{
    int food;
    public Dog(int food) {
        this.food = food;
    }

    @Override
    public int compareTo(Dog o) {
        if (this.food < o.food) return -1;
        else if (this.food > o.food) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "food=" + food +
                '}';
    }
}
