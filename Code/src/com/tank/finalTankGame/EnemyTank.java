package com.tank.finalTankGame;

import java.awt.*;
import java.util.Random;

//敌人坦克
class EnemyTank extends Tank implements Runnable{

    int speed = 4;

    public EnemyTank(int x, int y, Color color) {
        super(x, y ,color);
        super.setDirect(WarField.INFO.BACKWARD);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //坦克随机选个方向
            Random random = new Random();
//            WarField.INFO.valueOf(WarField.INFO.class,"FORWARD");
            int i = random.nextInt(4);
            switch (this.direct) {
                case FORWARD://正在向前走
                    this.y -= speed;break;
                case BACKWARD://正在向前走
                    this.y += speed;break;
                case LEFT://正在向前走
                    this.x -= speed;break;
                case RIGHT://正在向前走
                    this.x += speed;break;
            }
            this.setDirect(WarField.INFO.getIdByValue(i));
            //判断是否死亡  如果不看会出现僵尸线程   每次写线程需要看退出值
            if (this.isAlive == false) {
                break;
            }
        }
    }
}
