package com.mashibing.tank;

import java.awt.*;

/**
 * @Description com.mashibing.tank.Bullet
 * @Author Radish
 * @Date 2020-08-28 20:11
 */
public class Explode {
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;



    public boolean living = true;

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length) {
            TankFrame.INSTANCE.explodes.remove(this);
        }
    }
}
