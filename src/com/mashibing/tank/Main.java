package com.mashibing.tank;

import com.mashibing.tank.net.Client;

public class Main {

    public static void main(String[] args) throws Exception {
        TankFrame tf = TankFrame.INSTANCE;
        tf.setVisible(true);

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
