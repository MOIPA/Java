//package com.tank.component;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class DrawTank extends JFrame {
//    MyPanel mp = null;
//    public static void main(String[] args) {
//        new DrawTank();
//    }
//
//    public DrawTank() {
//        mp = new MyPanel();
//        this.add(mp);
//        this.setSize(400, 300);
//        this.setVisible(true);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//}
//
////面板 战场
//class MyPanel extends JPanel {
//    //应该有一个坦克存活在战场
//    HeroTank tank = null;
//
//    public MyPanel() {
//        tank = new HeroTank(100,100);
//    }
//
//    public void paint(Graphics g) {
//        super.paint(g);
//        //背景色
//        g.setColor(Color.BLACK);
//        g.fillRect(0,0,400,300);
//        drawATank(tank.getX(),tank.getY(),g,0,1);
//    }
//
//    //draw 一个坦克
//    public void drawATank(int x, int y, Graphics g,int direct,int type) {
//        switch (type) {
//            case 0:
//                g.setColor(Color.BLUE);
//                break;
//            case 1:
//                g.setColor(Color.RED);
//                break;
//        }
//        switch (direct) {
//            case 0://向上:
//                //画出坦克
//                //画出部件  后期需要封装
//                g.fill3DRect(x, y, 5, 30,false);
//                g.fill3DRect(x+15, y, 5, 30,false);
//                g.fill3DRect(x+5, y+5, 10, 20,false);
//                g.fillOval(x + 5, y + 10, 10, 10);
//                g.drawLine(x + 10, y + 15, x + 10, y);
//                break;
//
//        }
//
//
//    }
//}
//
//class Tank{
//    private int x; //坦克横坐标
//    private int y;
//
//    public int getX() {
//        return x;
//    }
//
//    public void setX(int x) {
//        this.x = x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public void setY(int y) {
//        this.y = y;
//    }
//
//    public Tank(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}
//class HeroTank extends Tank{
//
//    public HeroTank(int x, int y) {
//        super(x, y);
//    }
//}