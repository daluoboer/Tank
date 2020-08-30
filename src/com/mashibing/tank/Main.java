package com.mashibing.tank;

import com.mashibing.tank.abstractFactory.RectTank;

public class Main {

    public static void main(String[] args) throws Exception {
        TankFrame tf = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount")) ;

        for (int i = 0; i < initTankCount; i++) {
            tf.enemies.add(new Tank(50 + i * 80, 300,Dir.DOWN, Group.BAD,tf));
        }

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
