package com.mashibing.dp.factory;

/**
 * @Description CarFactory
 * @Author Radish
 * @Date 2020-08-30 11:02
 */
public class CarFactory {
    public Movable createCar() {
        System.out.println("a car created!");
        return new Car();
    }
}
