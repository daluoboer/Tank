package com.mashibing.dp.abstractFactory;

/**
 * @Description Main
 * @Author Radish
 * @Date 2020-08-30 10:54
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory f = new MagicFactory();
        Vehicle c = f.createVehicle();
        c.go();
        Weapon w = f.createWeapon();
        w.shoot();
        Food b = f.createFood();
        b.printName();
    }
}
