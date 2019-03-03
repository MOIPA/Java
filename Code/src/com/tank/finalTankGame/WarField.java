package com.tank.finalTankGame;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//面板 战场
class WarField extends JPanel implements KeyListener, Runnable {

    HeroTank playerTank = null;                           //定义主角坦克
    Vector<Image> boomPics = new Vector<Image>();           //定义战场爆炸图片
    Vector<Bomb> bombs = new Vector<Bomb>();               //定义战场炸弹
    Vector<EnemyTank> enemys = new Vector<EnemyTank>();         //定义敌人坦克

    //全局信息存放在此
    public enum INFO {
        LEFT(2), RIGHT(3), FORWARD(0), BACKWARD(1)
        , EnemyNumbers(4),WarFieldHeight(300),WarFieldWidth(400)
        ,EnemyBulleSpeed(5);

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

        public static INFO getIdByValue(int value) {
            switch (value) {
                case 1:return BACKWARD;
                case 2:return LEFT;
                case 3:return RIGHT;
                case 0:return FORWARD;
            }
            return null;
        }

    }

    public WarField() {
        //初始化主角
        playerTank = new HeroTank(100, 100, Color.RED);
//        this.addKeyListener(this);
        //初始化敌人坦克组
        for (int i = 1; i <= INFO.EnemyNumbers.getValue(); i++) {
            EnemyTank enemyTank = new EnemyTank(i * 40, 20, Color.YELLOW);
            //启动坦克
            new Thread(enemyTank).start();
            //坦克启动后发射一次
            enemyTank.enemyFire();
            enemys.add(enemyTank);
        }
        //初始化爆炸图片
//        System.out.println(getClass().getClassLoader().getResource("Boom1.jpg"));
        boomPics.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Boom1.jpg")));
        boomPics.add(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Boom2.jpg")));

    }

    //判断子弹是否击中坦克
    public void hitTank(Bullet bullet, Tank enemyTank) {
        switch (enemyTank.getDirect()) {
            case FORWARD:
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() >= bullet.getX() + 30 && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 20 >= bullet.getY()) {
                    //子弹死亡
                    bullet.isAlive = false;
                    //坦克死亡
                    enemyTank.isAlive = false;
                    //创建炸弹效果
                    bombs.add(new Bomb(enemyTank.getX(), enemyTank.getY()));
                }
//                System.out.println("enemy forward died");
                break;
            case LEFT:
//                System.out.println("enemy left died");
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() >= bullet.getX() + 30 && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 20 >= bullet.getY()) {
                    bullet.isAlive = false;
                    enemyTank.isAlive = false;
                    bombs.add(new Bomb(enemyTank.getX(), enemyTank.getY()));
                }
                break;
            case RIGHT:
//                System.out.println("enemy right died");
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() + 30 >= bullet.getX() && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 20 >= bullet.getY()) {
                    bullet.isAlive = false;
                    enemyTank.isAlive = false;
                    bombs.add(new Bomb(enemyTank.getX(), enemyTank.getY()));
                }
                break;
            case BACKWARD:
                if (enemyTank.getX() <= bullet.getX() && enemyTank.getX() + 20 >= bullet.getX() && enemyTank.getY() <= bullet.getY() && enemyTank.getY() + 30 >= bullet.getY()) {
                    bullet.isAlive = false;
                    enemyTank.isAlive = false;
                    bombs.add(new Bomb(enemyTank.getX(), enemyTank.getY()));
                }
                break;
        }
//        System.out.println("dected");

    }

    public void paint(Graphics g) {
        super.paint(g);
        //背景色
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, INFO.WarFieldWidth.getValue(), INFO.WarFieldHeight.getValue());
        //主角
        if(playerTank.isAlive)drawTank(playerTank.getX(), playerTank.getY(), g, playerTank.getDirect(), Color.RED);
        //画出敌人坦克
        drawEnemyTanksAndBullets(g);
        //绘画玩家发射的子弹
        drawPlayersBullets(g);
        //绘制场上炸弹爆炸效果  有炸弹就爆炸0.6s
        drawBoomEffect(g);

    }

    private void drawBoomEffect(Graphics g) {
        //绘制场上炸弹爆炸效果  有炸弹就爆炸0.6ms
        for (int i=0;i<bombs.size();i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.isAlive) {
                //为了防止第一次加载图片未完全载入内存导致显示不出
                Image img = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("Boom2.jpg"));
                MediaTracker t = new MediaTracker(this);
                t.addImage(img, 0);
                try {
                    t.waitForAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //前三次加载第一张图片  因为每100ms绘制一次  总共6次 炸弹持续时间为600ms
                if (bomb.lastTime > 3) g.drawImage(boomPics.get(0), bomb.x, bomb.y, 30, 30, this);
                else g.drawImage(boomPics.get(1), bomb.x, bomb.y, 30, 30, this);
                //爆炸持续时间减少
                bomb.decreaseTime();
            } else {
                bombs.remove(bomb);
                i--;
            }
        }
    }

    private void drawPlayersBullets(Graphics g) {
        //绘画玩家发射的子弹
        for (int i = 0; i < playerTank.bullets.size(); i++) {
            Bullet bullet = playerTank.bullets.get(i);
            if (bullet != null) {
                if (bullet.isAlive == false) {
                    playerTank.bullets.remove(bullet);//子弹死亡后在此释放资源
                    i--;
                }
                else g.draw3DRect(bullet.getX(), bullet.getY(), 1, 1, false);
            }
        }
    }

    //敌人 和 敌人的子弹
    private void drawEnemyTanksAndBullets(Graphics g) {
        //敌人 和 敌人的子弹
        EnemyTank enemyTank;
        for (int i = 0; i < enemys.size(); i++) {
            enemyTank = enemys.get(i);
            if (enemyTank.isAlive)
                drawTank(enemys.get(i).getX(), enemys.get(i).getY(), g, enemyTank.getDirect(), Color.YELLOW);
            else {
                enemys.remove(enemyTank);
                i--;
            }
            //画出敌人坦克的子弹
            for (int j = 0; j < enemyTank.enemyFiredBullets.size(); j++) {
                Bullet bullet = enemyTank.enemyFiredBullets.get(j);
                if (bullet.isAlive) {
                    g.draw3DRect(bullet.getX(), bullet.getY(), 1, 1, false);
                }else{
                    enemyTank.enemyFiredBullets.remove(bullet);//子弹死亡后在此释放资源
                    j--;
                }
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
            this.playerTank.setDirect(INFO.FORWARD);
            this.playerTank.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            this.playerTank.setDirect(INFO.RIGHT);
            this.playerTank.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            this.playerTank.setDirect(INFO.BACKWARD);
            this.playerTank.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            this.playerTank.setDirect(INFO.LEFT);
            this.playerTank.moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //开火  控制发射数量
            //TODO 后期可以改进 使用令牌桶算法
            if (this.playerTank.bullets.size() <= 5)
                this.playerTank.fire();

        }
//        this.playerTank.moveLeft();
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
            //判断是否击中敌人坦克 每个坦克和每个子弹
            checkHitEnemy(playerTank);
            //判断是否击中我方坦克
            checkHitPlayer(enemys, playerTank);

            this.repaint();

        }
    }

    private void checkHitPlayer(Vector<EnemyTank> enemys, HeroTank mainTank) {
        for (EnemyTank tank : enemys) {
            for (Bullet bullet : tank.enemyFiredBullets) {
                hitTank(bullet,mainTank);
            }
        }
    }

    private void checkHitEnemy(HeroTank mainTank) {
        for (int i = 0; i < mainTank.bullets.size(); i++) {
            Bullet bullet = mainTank.bullets.get(i);
            if (bullet.isAlive == true) {
                //取出每个坦克匹配
                for (int j = 0; j < enemys.size(); j++) {
                    EnemyTank enemyTank = enemys.get(j);
                    if (enemyTank.isAlive)
                        hitTank(bullet, enemyTank); //paint的时候根据状态选择是否绘画
                }
            }
        }
    }
}

