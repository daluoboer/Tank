package com.mashibing.tank;

import com.mashibing.tank.cor.ColliderChain;
import com.mashibing.tank.cor.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description GameModel
 * @Author Radish
 * @Date 2020-08-30 19:16
 */
public class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }
    Tank myTank;//TODO:为啥把这个放到外面就会报错呢？不是先执行static的内容吧？那为什么是这个先new才执行上面的static呢？
    ColliderChain chain = new ColliderChain();
    List<GameObject> objects = new ArrayList<>();

    static final int GAME_WIDTH = Integer.parseInt((String)PropertyMgr.get("gameWidth")) , GAME_HEIGHT = Integer.parseInt((String)PropertyMgr.get("gameHeight"));


    public static GameModel getInstance() {
        return INSTANCE;
    }
    private GameModel() {
    }

    private void init() {
        int myTankX = Integer.parseInt((String)PropertyMgr.get("myTankX")) ;
        int myTankY = Integer.parseInt((String)PropertyMgr.get("myTankY")) ;
        int enemyY = Integer.parseInt((String)PropertyMgr.get("enemyY")) ;

        myTank = new Tank(myTankX, myTankY, Dir.DOWN, Group.GOOD);
        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount")) ;

//        objects.add(myTank);
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, enemyY,Dir.DOWN, Group.BAD);
        }

        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        /*g.drawString("子弹的数量：" + bullets.size(),100,60);
        g.drawString("敌人的数量：" + enemies.size(), 100, 80);
        g.drawString("爆炸的数量：" + explodes.size(), 100, 100);
        */
        g.setColor(c);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1,o2);
            }
        }
    }

    public Tank getMainTank() {
        return myTank;
    }
}
