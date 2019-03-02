package com.tank.shoot;

public class Bomb {
    int x;
    int y;
    int lastTime = 6;
    boolean isAlive = true;
    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void decreaseTime() {
        if (lastTime > 0) {
            lastTime--;
        } else {
            this.isAlive=false;
        }
    }
}
