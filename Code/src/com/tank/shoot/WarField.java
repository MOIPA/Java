package com.tank.shoot;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import com.tank.shoot.WarField.INFO.*;

//面板 战场
class WarField extends JPanel implements KeyListener, Runnable {

    public enum INFO {
        LEFT(2),RIGHT(3),FORWARD(0),BACKWARD(1),ENEMYNUMBERS(4);
        private int value;

        private INFO(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    //定义主角坦克
    HeroTank tank = null;
    //定义敌人坦克
    Vector<EnemyTank> enemys = new Vector<EnemyTank>();
    //敌人数量

    int enemyNumber = INFO.ENEMYNUMBERS.getValue();

    public WarField() {
        //初始化主角
        tank = new HeroTank(100, 100, Color.RED);
//        this.addKeyListener(this);
        //初始化敌人坦克组
        for (int i = 1; i <= enemyNumber; i++) {
            enemys.add(new EnemyTank(i * 40, 20, Color.YELLOW));
        }
    }

    //判断子弹是否击中坦克
    public void hitTank(Bullet bullet, EnemyTank enemyTank) {
        switch (enemyTank.getDirect()) {
            case FORWARD:
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() >= bullet.getX() + 30 && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 20 >= bullet.getY()) {
                    //子弹死亡
                    bullet.isAlive = false;
                    //坦克死亡
                    enemyTank.isAlive = false;
                }
                break;
            case LEFT:
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() >= bullet.getX() + 30 && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 20 >= bullet.getY()) {
                    bullet.isAlive = false;
                    enemyTank.isAlive = false;
                }
                break;
            case RIGHT:
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() >= bullet.getX() + 20 && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 30 >= bullet.getY()) {
                    bullet.isAlive = false;
                    enemyTank.isAlive = false;
                }
                break;
            case BACKWARD:
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() >= bullet.getX() + 30 && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 20 >= bullet.getY()) {
                    bullet.isAlive = false;
                    enemyTank.isAlive = false;
                }
                break;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        //背景色
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 400, 300);
        //主角
        drawTank(tank.getX(), tank.getY(), g, tank.getDirect(), Color.RED);
        //小兵
        for (int i = 0; i < enemys.size(); i++) {
            drawTank(enemys.get(i).getX(), enemys.get(i).getY(), g, INFO.BACKWARD, Color.YELLOW);
        }
        //子弹
        for (int i = 0; i < tank.bullets.size(); i++) {
            if (tank.bullets.get(i) != null) {
                if (tank.bullets.get(i).isAlive == false) tank.bullets.remove(i);  //死亡后在此销毁资源
                else g.draw3DRect(tank.bullets.get(i).getX(), tank.bullets.get(i).getY(), 1, 1, false);
            }
        }
    }

    //draw 坦克
    public void drawTank(int x, int y, Graphics g, INFO direct, Color color) {
//        switch (type) {
//            case 0:
//                g.setColor(Color.BLUE);
//                break;
//            case 1:
//                g.setColor(Color.RED);
//                break;
//        }
        g.setColor(color);
        switch (direct) {
            case FORWARD://向上:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;

            case RIGHT://向you:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x + 30, y + 10);
                break;
            case BACKWARD://向xia:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y + 30);
                break;
            case LEFT://向zuo:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x, y + 10);
                break;
        }


    }


    //处理按键wsad
    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("key typed");
        keyPressed(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("key pressed");
        //设置坦克方向
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.tank.setDirect(INFO.FORWARD);
            this.tank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.tank.setDirect(INFO.RIGHT);
            this.tank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.tank.setDirect(INFO.BACKWARD);
            this.tank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.tank.setDirect(INFO.LEFT);
            this.tank.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //开火  控制发射数量
            //TODO 后期可以改进 使用令牌桶算法
            if (this.tank.bullets.size() <= 5)
                this.tank.fire();

        }
//        this.tank.moveLeft();
        //坦克移动后要刷新面板
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("key released");
    }

    @Override
    public void run() {
        //每隔100ms重绘界面
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}

