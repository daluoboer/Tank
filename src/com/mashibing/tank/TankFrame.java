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
        GameModel.getInstance().paint(g);
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
                    GameModel.getInstance().getMainTank().goFire();
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
                GameModel.getInstance().getMainTank().setMoving(false);
            } else {
                GameModel.getInstance().getMainTank().setMoving(true);
                if (bL) GameModel.getInstance().getMainTank().setDir(Dir.LEFT);
                if (bU) GameModel.getInstance().getMainTank().setDir(Dir.UP);
                if (bR) GameModel.getInstance().getMainTank().setDir(Dir.RIGHT);
                if (bD) GameModel.getInstance().getMainTank().setDir(Dir.DOWN);
            }
        }
    }
}
