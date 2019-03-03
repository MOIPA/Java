package com.tank.finalTankGame;

import java.awt.*;
import java.util.Random;
import java.util.Vector;

import static com.tank.finalTankGame.WarField.INFO.*;
import static com.tank.finalTankGame.WarField.INFO.LEFT;

//敌人坦克
class EnemyTank extends Tank implements Runnable {

    //敌人发射后的子弹
    Vector<Bullet> enemyFiredBullets = new Vector<Bullet>();

    int speed = 2;
    int times = 0;
    public EnemyTank(int x, int y, Color color) {
        super(x, y, color);
        super.setDirect(WarField.INFO.BACKWARD);
    }

    @Override
    public void run() {
        while (true) {
            times++;
            //坦克随机选个方向  注意的是坦克先走完再换方向
            Random random = new Random();
//            WarField.INFO.valueOf(WarField.INFO.class,"FORWARD");
            int i = random.nextInt(4);
            //坦克自动行走
            switch (this.direct) {
                case FORWARD://正在向前走
                    for (int j = 0; j < 20; j++) {
                        if (y > 0) this.y -= speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case BACKWARD:
                    for (int j = 0; j < 20; j++) {
                        if (y < WarField.INFO.WarFieldHeight.getValue() - 30) this.y += speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case LEFT:
                    for (int j = 0; j < 20; j++) {
                        if (x > 0) this.x -= speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case RIGHT:
                    for (int j = 0; j < 20; j++) {
                        if (x < WarField.INFO.WarFieldWidth.getValue() - 30) this.x += speed;
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            this.setDirect(WarField.INFO.getIdByValue(i));
            //判断是否死亡  如果不看会出现僵尸线程   每次写线程需要看退出值
            if (this.isAlive == false) {
                break;
            }
            //走了一段路后 看能不能发射子弹 发射子弹的时机
            if(times%2==0)this.enemyFire(); //这个方法会自己判断能不能发射子弹 是否在限制内


        }
    }

    public void enemyFire() {
        if(enemyFiredBullets.size()>3)return;   //每个坦克不能一次有三颗子弹在场
        Bullet bullet = null;
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
        new Thread(bullet).start();
        this.enemyFiredBullets.add(bullet);
    }
}
