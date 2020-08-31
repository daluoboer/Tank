package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.Tank;

/**
 * @Description TankTankCollider
 * @Author Radish
 * @Date 2020-08-30 20:09
 */
public class TankTankCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;

            if (t1.getGroup() != t2.getGroup() && t1.rect.intersects(t2.rect)) {
                System.out.println("yeah died!");
                //是敌我双方应该俩都死
                t1.die();
                t2.die();
                return true;
            }

            if (t1.rect.intersects(t2.rect)) {
                t1.back();
                t2.back();
                return true;
            }
        }
        return false;
    }



}
