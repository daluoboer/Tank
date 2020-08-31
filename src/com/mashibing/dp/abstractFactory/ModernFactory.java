package com.mashibing.dp.abstractFactory;

/**
 * @Description ModernFactory
 * @Author Radish
 * @Date 2020-08-30 11:29
 */
public class ModernFactory extends AbstractFactory {
    @Override
    Food createFood() {
        return new Bread();
    }

    @Override
    Vehicle createVehicle() {
        return new Car();
    }

    @Override
    Weapon createWeapon() {
        return new AK47();
    }
}
