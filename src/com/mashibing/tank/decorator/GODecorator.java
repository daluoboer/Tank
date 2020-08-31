package com.mashibing.tank.decorator;

import com.mashibing.tank.GameObject;

import java.awt.*;

/**
 * @Description GODecorator
 * @Author Radish
 * @Date 2020-08-31 08:36
 */
public abstract class GODecorator extends GameObject {
    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }
}
