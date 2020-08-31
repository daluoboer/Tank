package com.mashibing.dp.abstractFactory;

/**
 * @Description ModernFactory
 * @Author Radish
 * @Date 2020-08-30 11:29
 */
public class MagicFactory extends AbstractFactory {
    @Override
    Food createFood() {
        return new MushRoom();
    }

    @Override
    Vehicle createVehicle() {
        return new Broom();
    }

    @Override
    Weapon createWeapon() {
        return new MagicStick();
    }
}
