package com.mashibing.dp.factory;

/**
 * @Description SimpleVehicleFactory
 * 简单工厂
 * 可扩展性不好
 * @Author Radish
 * @Date 2020-08-30 11:00
 */
public class SimpleVehicleFactory {
    public Car createCar(){
        return new Car();
    }

    public Plane createPlane() {
        return new Plane();
    }

    public Broom createBroom() {
        return new Broom();
    }
}
