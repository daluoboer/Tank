package com.mashibing.tank.cor;

import com.mashibing.tank.GameObject;
import com.mashibing.tank.PropertyMgr;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description ColliderChain
 * @Author Radish
 * @Date 2020-08-30 20:35
 */
public class ColliderChain implements Collider {
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        String colliders = (String) PropertyMgr.get("colliders");
        String[] cs = colliders.split(",");
        if (cs.length < 1) return;
        try {
            for (String s : cs) {
                add((Collider) Class.forName(s).newInstance());
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void add(Collider c) {
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (colliders.get(i).collide(o1,o2)) continue;
        }
        return true;
    }
}
