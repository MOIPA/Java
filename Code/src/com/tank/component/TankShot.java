package com.tank.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * 增加射击功能 且动起来
 *
 *  由于在软件开发初期，需求工程做的比较好，业务逻辑和目标都较为明确，分工也比较合理，所以我们进度一直保持在掌控之中
 *  但在软件开发过程中也出现过一些技术和需求的问题比如服务器api文档不明确导致调用时不知道输出格式，即时通讯系统中线程执行的调度问题
 *  为了解决这个问题我们合作重写了系统的详细设计文档，在文档里明确规范了调用的输入和输出规范，并且每个都给出了调用示例，
 *  对于混合开发框架下的线程问题我们详细参考了官方的api文档，通过示例摸索出了解决方案
 *  在沟通协作下我们成功完成了整个工程
 *
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