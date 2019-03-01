package com.tank.shoot;

import java.awt.*;

class Tank{
    public boolean isAlive = true;
    int x; //坦克横坐标
    int y;
    //    int direct=0; //方向 0 上 1 右 2下  3 左
    WarField.INFO direct = WarField.INFO.FORWARD;
    Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public WarField.INFO getDirect() {
        return direct;
    }

    public void setDirect(WarField.INFO direct) {
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

    public Tank(int x, int y,Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}
