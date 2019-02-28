package com.tank.component;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//面板 战场
class MyPanel extends JPanel implements KeyListener {
    //应该有一个坦克存活在战场
    HeroTank tank = null;

    public MyPanel() {
        tank = new HeroTank(100,100);
//        this.addKeyListener(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        //背景色
        g.setColor(Color.BLACK);
        g.fillRect(0,0,400,300);
        drawATank(tank.getX(),tank.getY(),g,tank.getDirect(),1);
    }

    //draw 一个坦克
    public void drawATank(int x, int y, Graphics g,int direct,int type) {
        switch (type) {
            case 0:
                g.setColor(Color.BLUE);
                break;
            case 1:
                g.setColor(Color.RED);
                break;
        }
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
//        this.tank.moveLeft();
        //坦克移动后要刷新面板
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        System.out.println("key released");
    }
}

class Tank{
    int x; //坦克横坐标
    int y;
    int direct=0; //方向 0 上 1 右 2下  3 左

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
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

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

//敌人坦克
//class EnemyTank


//玩家坦克
class HeroTank extends Tank{
    //坦克速度
    int speed = 4;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public HeroTank(int x, int y) {
        super(x, y);
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
