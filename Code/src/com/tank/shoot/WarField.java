package com.tank.shoot;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//面板 战场
class WarField extends JPanel implements KeyListener,Runnable {
    //定义主角坦克
    HeroTank tank = null;
    //定义敌人坦克
    Vector<EnemyTank> enemys = new Vector<EnemyTank>();
    //敌人数量
    int enemyNumber = 4;

    public WarField() {
        //初始化主角
        tank = new HeroTank(100,100,Color.RED);
//        this.addKeyListener(this);
        //初始化敌人坦克组
        for (int i = 1; i <= enemyNumber; i++) {
            enemys.add(new EnemyTank(i * 40, 20,Color.YELLOW));
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        //背景色
        g.setColor(Color.BLACK);
        g.fillRect(0,0,400,300);
        //主角
        drawTank(tank.getX(), tank.getY(), g, tank.getDirect(), Color.RED);
        //小兵
        for (int i = 0; i < enemys.size(); i++) {
            drawTank(enemys.get(i).getX(),enemys.get(i).getY(),g,2,Color.YELLOW);
        }
        //子弹
        if (tank.bullet != null&&tank.bullet.isAlive==true) {
            g.draw3DRect(tank.bullet.getX(), tank.bullet.getY(), 1, 1, false);
        }
    }

    //draw 坦克
    public void drawTank(int x, int y, Graphics g, int direct, Color color) {
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
            case 0://向上:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 5, 30,false);
                g.fill3DRect(x+15, y, 5, 30,false);
                g.fill3DRect(x+5, y+5, 10, 20,false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y);
                break;

            case 1://向you:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 30, 5,false);
                g.fill3DRect(x, y+15, 30, 5,false);
                g.fill3DRect(x+5, y+5, 20, 10,false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x + 30, y+10);
                break;
            case 2://向xia:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 5, 30,false);
                g.fill3DRect(x+15, y, 5, 30,false);
                g.fill3DRect(x+5, y+5, 10, 20,false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y+30);
                break;
            case 3://向zuo:
                //画出坦克
                //画出部件  后期需要封装
                g.fill3DRect(x, y, 30, 5,false);
                g.fill3DRect(x, y+15, 30, 5,false);
                g.fill3DRect(x+5, y+5, 20, 10,false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 15, y + 10, x , y+10);
                break;
        }


    }


    //处理按键wsad
    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("key typed");
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("key pressed");
        //设置坦克方向
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.tank.setDirect(0);
            this.tank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.tank.setDirect(1);
            this.tank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.tank.setDirect(2);
            this.tank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.tank.setDirect(3);
            this.tank.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //开火
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

