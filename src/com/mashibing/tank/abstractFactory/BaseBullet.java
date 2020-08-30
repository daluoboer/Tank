package com.mashibing.tank.abstractFactory;

import com.mashibing.tank.Tank;

import java.awt.*;

/**
 * @Description BaseBullet
 * @Author Radish
 * @Date 2020-08-30 13:14
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);
}
