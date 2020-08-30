package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description com.mashibing.tank.TankFrame
 * @Author Radish
 * @Date 2020-08-28 15:14
 */
public class TankFrame extends Frame {
    GameModel gm = new GameModel();

    public TankFrame() throws HeadlessException {
        setSize(GameModel.GAME_WIDTH,GameModel.GAME_HEIGHT);
        setResizable(false);
        setTitle("com.mashibing.tank.Tank War");
        setVisible(true);
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
            offScreenImage = this.createImage(GameModel.GAME_WIDTH,GameModel.GAME_HEIGHT);
        }
        Graphics gOffScrean = offScreenImage.getGraphics();
        Color c = gOffScrean.getColor();
        gOffScrean.setColor(Color.BLACK);
        gOffScrean.fillRect(0,0,GameModel.GAME_WIDTH,GameModel.GAME_HEIGHT);
        gOffScrean.setColor(c);
        paint(gOffScrean);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);
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
                    gm.getMainTank().fire();
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
                gm.getMainTank().setMoving(false);
            } else {
                gm.getMainTank().setMoving(true);
                if (bL) gm.getMainTank().setDir(Dir.LEFT);
                if (bU) gm.getMainTank().setDir(Dir.UP);
                if (bR) gm.getMainTank().setDir(Dir.RIGHT);
                if (bD) gm.getMainTank().setDir(Dir.DOWN);
            }
        }
    }
}
