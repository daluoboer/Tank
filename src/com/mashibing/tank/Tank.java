package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Description com.mashibing.tank.Tank
 * @Author Radish
 * @Date 2020-08-28 19:30
 */
public class Tank extends GameObject{
    public Rectangle rect = new Rectangle();
    Dir dir = Dir.DOWN;
    private static final int SPEED = 1;
    private boolean moving = true;
    private boolean living = true;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();

    private Random random = new Random();

    private Group group = Group.BAD;

    FireStrategy fs;

    private int lastX, lastY;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        if (group == Group.GOOD) {
            String goodFsName = (String)PropertyMgr.get("goodFs");
            try {
                fs = (FireStrategy) Class.forName(goodFsName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            fs = new DefaultFireStrategy();
        }
        GameModel.getInstance().add(this);
    }


    public void paint(Graphics g) {
        if (!living) {
            GameModel.getInstance().remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD,x,y,null);
                break;
        }
        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        lastX = x;
        lastY = y;
        if (!moving) return;
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.goFire();
            randomDir();
        }

        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > GameModel.GAME_WIDTH - Tank.WIDTH - 2) x = GameModel.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > GameModel.GAME_HEIGHT - Tank.HEIGHT - 2) y = GameModel.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void goFire() {
        fs.fire(this);
    }

    public void die() {
        this.living = false;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void stop() {
        moving = false;
    }

    public void back() {
        x = lastX;
        y = lastY;
    }
}
