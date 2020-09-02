package com.mashibing.tank;

import com.mashibing.tank.net.Client;

public class Main {

    public static void main(String[] args) throws Exception {
        TankFrame tf = TankFrame.INSTANCE;
        tf.setVisible(true);

        /*int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount")) ;

        for (int i = 0; i < initTankCount; i++) {
            tf.enemies.add(new Tank(50 + i * 80, 300,Dir.DOWN, Group.BAD,tf));
        }*/

        new Thread(()-> {
			while(true) {
				try {
					Thread.sleep(25);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tf.repaint();
			}
		}).start();
        
        Client c = Client.INSTANCE;
        c.connect();
    }
    
}
