package com.mashibing.tank.abstractFactory;

import com.mashibing.tank.Group;

import java.awt.*;

/**
 * @Description BaseTank
 * @Author Radish
 * @Date 2020-08-30 13:14
 */
public abstract class BaseTank {
    public Rectangle rect = new Rectangle();

    public abstract void paint(Graphics g);

    public abstract Group getGroup();

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}
