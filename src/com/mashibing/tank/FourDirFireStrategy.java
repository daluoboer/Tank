package com.mashibing.tank;

/**
 * @Description FourDirFireStrategy
 * @Author Radish
 * @Date 2020-08-30 10:26
 */
public class FourDirFireStrategy implements FireStrategy{
    /*private static final FourDirFireStrategy INSTANCE = new FourDirFireStrategy();
    private FourDirFireStrategy(){}
    public static FourDirFireStrategy getInstance() {
        return INSTANCE;
    }*/
    @Override
    public void fire(Tank t) {
        int bX = t.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = t.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (Dir dir : dirs) {
            new Bullet(bX, bY, dir,t.group,t.gm);
        }
    }
}
