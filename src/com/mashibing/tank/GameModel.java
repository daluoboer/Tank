package com.mashibing.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description GameModel
 * @Author Radish
 * @Date 2020-08-30 19:16
 */
public class GameModel {
    Tank myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD, this);
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Tank> enemies = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public GameModel() {
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount")) ;

        for (int i = 0; i < initTankCount; i++) {
            enemies.add(new Tank(50 + i * 80, 100,Dir.DOWN, Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + bullets.size(),100,60);
        g.drawString("敌人的数量：" + enemies.size(), 100, 80);
        g.drawString("爆炸的数量：" + explodes.size(), 100, 100);
        g.setColor(c);
        myTank.paint(g);
        //画
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞检测 collision detect
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemies.size(); j++) {
                bullets.get(i).collideWith(enemies.get(j));
            }
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
        return myTank;
    }
}
