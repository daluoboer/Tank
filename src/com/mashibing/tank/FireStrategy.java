package com.mashibing.tank;

import com.mashibing.tank.abstractFactory.BaseTank;

/**
 * @Description FireStrategy
 * @Author Radish
 * @Date 2020-08-30 10:16
 */
public interface FireStrategy {
    void fire(BaseTank t);
}
