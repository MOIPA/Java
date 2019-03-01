package com.tank.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * 增加射击功能 且动起来
 */
public class TankShot extends JFrame {
    MyPanel mp = null;
    public static void main(String[] args) {
        new TankShot();
    }

    public TankShot() {
        mp = new MyPanel();
        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(400, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}