package com.mashibing.tank.abstractFactory;

import com.mashibing.tank.ResourceMgr;
import com.mashibing.tank.TankFrame;

import java.awt.*;

/**
 * @Description RectExplode
 * @Author Radish
 * @Date 2020-08-30 13:26
 */
public class RectExplode extends BaseExplode {
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;


    private TankFrame tf;

    public boolean living = true;

    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
//        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= 15) {
            tf.explodes.remove(this);
        }
        g.setColor(c);
    }
}
