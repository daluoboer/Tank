package com.mashibing.dp.observer;

/**
 * @Description Child
 * @Author Radish
 * @Date 2020-08-31 10:36
 */
public class Child {
    private boolean cry = false;

    public boolean isCry() {return cry;}

    public void wakeUp() {
        System.out.println("Waked up! Crying wuwuwuwuwuwu....");
    }

    public static void main(String[] args) {
        Child child = new Child();
        while (!child.isCry()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("observing.......");
        }
    }
}
