package com.mashibing.dp.factoryMethod;

/**
 * @Description Main
 * @Author Radish
 * @Date 2020-08-30 10:54
 */
public class Main {
    public static void main(String[] args) {
        Car c = new Car();
        c.go();
        Plane p = new Plane();
        p.go();

        Movable m = new CarFactory().createCar();
        m.go();
    }
}
