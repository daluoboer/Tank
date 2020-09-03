package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import com.mashibing.tank.net.Client;
import com.mashibing.tank.net.TankStartMovingMsg;

/**
 * @Description com.mashibing.tank.TankFrame
 * @Author Radish
 * @Date 2020-08-28 15:14
 */
public class TankFrame extends Frame {
	public static final TankFrame INSTANCE = new TankFrame();
	
	Random r = new Random();
	
    Tank myTank = new Tank(r.nextInt(GAME_WIDTH), r.nextInt(GAME_HEIGHT), Dir.DOWN, Group.GOOD, this);
    List<Bullet> bullets = new ArrayList<Bullet>();

    Map<UUID,Tank> tanks = new HashMap<>();
    Bullet b = new Bullet(300,300,Dir.DOWN,Group.GOOD,this);
    List<Explode> explodes = new ArrayList<>();
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    private TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
//        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScrean = offScreenImage.getGraphics();
        Color c = gOffScrean.getColor();
        gOffScrean.setColor(Color.BLACK);
        gOffScrean.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScrean.setColor(c);
        paint(gOffScrean);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("bullets:" + bullets.size(), 10, 60);
		g.drawString("tanks:" + tanks.size(), 10, 80);
		g.drawString("explodes" + explodes.size(), 10, 100);
        g.setColor(c);
        myTank.paint(g);
        //画
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        tanks.values().stream().forEach((e)->e.paint(g));

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞检测 collision detect        
        for(int i=0; i<bullets.size(); i++) {
			for(int j = 0; j<tanks.size(); j++) 
				bullets.get(i).collideWith(tanks.get(j));
		}

        Explode e = new Explode(100,100,this);

//        e.paint(g);
        /*for (com.mashibing.tank.Bullet b : bullets) {
            b.paint(g);
        }*/

        /*for (Iterator<com.mashibing.tank.Bullet> it = bullets.iterator();it.hasNext();) {
            com.mashibing.tank.Bullet b = it.next();
            if (!b.living) it.remove();
        }*/
    }
    
    public Tank getMainTank() {
		return this.myTank;
	}
    
    public void addTank(Tank t) {
		tanks.put(t.getId(),t);
	}
	
	public Tank findByUUID(UUID id) {
		return tanks.get(id);
	}

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
            Client.INSTANCE.send(new TankStartMovingMsg(getMainTank()));
        }
    }



}
