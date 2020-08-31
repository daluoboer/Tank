package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;

/**
 * @Description Collider
 * @Author Radish
 * @Date 2020-08-30 20:06
 */
public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
