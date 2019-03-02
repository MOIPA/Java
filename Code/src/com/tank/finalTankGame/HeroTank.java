package com.tank.finalTankGame;

import java.awt.*;
import java.util.Vector;
import static com.tank.finalTankGame.WarField.INFO.*;


//玩家坦克
class HeroTank extends Tank {
    //子弹
    Bullet bullet =null;
    Vector<Bullet> bullets = new Vector<Bullet>();
    //坦克速度
    int speed = 4;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public HeroTank(int x, int y, Color color) {
        super(x, y, color);
    }

    //开火
    public void fire() {
        switch (this.getDirect()) {
            case FORWARD:
                bullet = new Bullet(this.getX() + 10, this.getY(), FORWARD, 5);
                break;
            case RIGHT:
                bullet = new Bullet(this.getX() + 30, this.getY() + 10, RIGHT, 5);
                break;
            case BACKWARD:
                bullet = new Bullet(this.getX() + 10, this.getY() + 30, BACKWARD, 5);
                break;
            case LEFT:
                bullet = new Bullet(this.getX(), this.getY() + 10, LEFT, 5);
                break;
        }
        bullets.add(bullet);
        //生成子弹后 子弹启动线程
        Thread t = new Thread(bullet);
        t.start();
    }

    //先上运动
    public void moveUp() {
        this.y -= this.speed;
    }

    public void moveDown() {
        this.y += this.speed;
    }

    public void moveLeft() {
        this.x -= this.speed;
    }

    public void moveRight() {
        this.x += this.speed;
    }
}
