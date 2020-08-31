package com.mashibing.tank;

import java.awt.*;

/**
 * @Description GameObject
 * @Author Radish
 * @Date 2020-08-30 19:45
 */
public abstract class GameObject {
    public int x;
    public int y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}
