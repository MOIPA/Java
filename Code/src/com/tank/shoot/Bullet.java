package com.tank.shoot;

import static com.tank.shoot.WarField.*;

public class Bullet implements Runnable{
    int x;
    int y;
    INFO direct;
    int speed;
    boolean isAlive = true;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public INFO getDirect() {
        return direct;
    }

    public void setDirect(INFO direct) {
        this.direct = direct;
    }

    public Bullet(int x, int y, INFO direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
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

    @Override
    public void run() {
        while (true) {
            //子弹每50ms动一次
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (this.getDirect()) {
                case FORWARD:
                    this.setY(this.getY() - this.speed);
                    break;
                case RIGHT:
                    this.setX(this.getX() + this.speed);
                    break;
                case BACKWARD:
                    this.setY(this.getY() + this.speed);
                    break;
                case LEFT:
                    this.setX(this.getX() - this.speed);
                    break;
            }
            //TODO 子弹死亡时间
            if (x <= 0 || x >= 400 || y <= 0 || y >= 300) {
                this.isAlive = false;
                break;
            }
        }
    }
}
