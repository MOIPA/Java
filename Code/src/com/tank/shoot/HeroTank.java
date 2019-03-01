package com.tank.shoot;

import java.awt.*;

//玩家坦克
class HeroTank extends Tank {
    //子弹
    Bullet bullet =null;
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
            case 0:
                bullet = new Bullet(this.getX() + 10, this.getY(),0,5);
                break;
            case 1:
                bullet = new Bullet(this.getX() + 30, this.getY() + 10,1,5);
                break;
            case 2:
                bullet = new Bullet(this.getX() + 10, this.getY() + 30,2,5);
                break;
            case 3:
                bullet = new Bullet(this.getX(), this.getY() + 10,3,5);
                break;
        }
        //生成子弹后 子弹启动线程
        Thread t = new Thread(bullet);
        t.start();
    }

    //先上运动
    public void moveUp() {
        this.y-=this.speed;
    }
    public void moveDown() {
        this.y+=this.speed;
    }
    public void moveLeft() {
        this.x-=this.speed;
    }
    public void moveRight() {
        this.x+=this.speed;
    }
}
