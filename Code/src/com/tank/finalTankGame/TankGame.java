package com.tank.finalTankGame;

import javax.swing.*;


/**
 * 增加射击功能
 */
public class TankGame extends JFrame {
    WarField mp = null;
    public static void main(String[] args) {
        new TankGame();
    }

    public TankGame() {
        //战场也是一个线程，不断刷新战场界面，战场也是一个监听按钮的事件处理者，按键时刷新战场
        mp = new WarField();
        //启动战场刷新界面线程
        new Thread(mp).start();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}