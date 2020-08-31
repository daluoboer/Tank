package com.mashibing.dp.abstractFactory;

/**
 * @Description AbstractFactory
 * @Author Radish
 * @Date 2020-08-30 11:04
 */
public abstract class AbstractFactory {
    abstract Food createFood();
    abstract Vehicle createVehicle();
    abstract Weapon createWeapon();
}
