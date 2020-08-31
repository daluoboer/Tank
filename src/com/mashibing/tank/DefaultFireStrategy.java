package com.mashibing.tank;

import com.mashibing.tank.decorator.RectDecorator;
import com.mashibing.tank.decorator.TailDecorator;

/**
 * @Description DefaultFireStrategy
 * @Author Radish
 * @Date 2020-08-30 10:16
 */
public class DefaultFireStrategy implements FireStrategy {
    /*private static final DefaultFireStrategy INSTANCE = new DefaultFireStrategy();
    private DefaultFireStrategy(){}
    public static DefaultFireStrategy getInstance() {
        return INSTANCE;
    }*/
    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        //TODO:把自己又加了一遍
        GameModel.getInstance().add(new RectDecorator(new TailDecorator(new Bullet(bX, bY, t.dir,t.getGroup()))));
    }
}
