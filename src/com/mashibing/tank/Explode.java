package com.mashibing.tank;

import java.awt.*;

/**
 * @Description com.mashibing.tank.Bullet
 * @Author Radish
 * @Date 2020-08-28 20:11
 */
public class Explode extends GameObject{
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    
    public boolean living = true;

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length) {
            GameModel.getInstance().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
