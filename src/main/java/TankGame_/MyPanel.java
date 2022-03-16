package TankGame_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Obamazzz
 * @version 2.0
 * 绘图区域
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义坦克
    Hero hero;
    Tank Enemy1;
    Tank Enemy2;
    Tank Enemy3;
    Shot myShot;

    public MyPanel() {
        hero = new Hero(100, 100);//初始化自己坦克
        Enemy1 = new Tank(200, 100);
        Enemy2 = new Tank(300, 100);
        Enemy3 = new Tank(400, 100);

    }

    @Override
    public void paint(Graphics g) {//画板
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //画出坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 0);//自己的坦克
        drawTank(Enemy1.getX(), Enemy1.getY(), g, 2, 1);
        drawTank(Enemy2.getX(), Enemy2.getY(), g, 2, 1);
        drawTank(Enemy3.getX(), Enemy3.getY(), g, 2, 1);
        //画出子弹
        g.setColor(Color.WHITE);//白色子弹
        for (int i = 0; i < hero.shotList.size(); i++) {
            if (hero.shot != null && hero.shot.isLive()) {
//            g.fill3DRect(hero.shot.getX(), hero.shot.getY(), 2, 2, false);
                drawBullet(hero.shotList.get(i).getX(), hero.shotList.get(i).getY(), g, hero.shotList.get(i).getDirect());
            }else {
                hero.shotList.remove(hero.shotList.get(i));//将已经死亡的子弹移除vector集合中
            }
        }

    }

    /**
     * 绘制坦克方法
     *
     * @param x         坦克左上角x坐标
     * @param y         坦克左上角y坐标
     * @param g         画笔
     * @param direction 坦克的朝向
     * @param type      坦克的类型
     */
    public void drawTank(int x, int y, Graphics g, int direction, int type) {
        switch (type) {//根据类型设置坦克颜色
            case 0:// 自己的坦克
                g.setColor(Color.BLUE);
                break;
            case 1:// 敌人的坦克
                g.setColor(Color.RED);
                break;
        }
        // 根据坦克方向来绘制坦克
        switch (direction) {
            case 0: // 向上
                g.fill3DRect(x, y, 10, 60, false);//左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆盖
                g.drawLine(x + 20, y, x + 20, y + 30);//炮筒
                break;
            case 1://向右
                g.fill3DRect(x - 10, y + 10, 60, 10, false);//左轮
                g.fill3DRect(x - 10, y + 40, 60, 10, false);//右轮
                g.fill3DRect(x, y + 20, 40, 20, false);//坦克中间
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆盖
                g.drawLine(x + 20, y + 30, x + 50, y + 30);//炮筒
                break;
            case 2://向下
                g.fill3DRect(x, y, 10, 60, false);//左轮
                g.fill3DRect(x + 30, y, 10, 60, false);//右轮
                g.fill3DRect(x + 10, y + 10, 20, 40, false);//坦克中间
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆盖
                g.drawLine(x + 20, y + 60, x + 20, y + 30);//炮筒
                break;
            case 3://向左
                g.fill3DRect(x - 10, y + 10, 60, 10, false);//左轮
                g.fill3DRect(x - 10, y + 40, 60, 10, false);//右轮
                g.fill3DRect(x, y + 20, 40, 20, false);//坦克中间
                g.fillOval(x + 10, y + 20, 20, 20);//画出圆盖
                g.drawLine(x + 20, y + 30, x - 10, y + 30);//炮筒
                break;
            default:
                break;
        }
    }

    public void drawBullet(int x, int y, Graphics g, int direction) {
        switch (direction) {
            case 0:
                g.drawLine(x, y, x, y - 2);
                break;
            case 1:
                g.drawLine(x, y, x + 2, y);
                break;
            case 2:
                g.drawLine(x, y, x, y + 2);
                break;
            case 3:
                g.drawLine(x, y, x - 2, y);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.setDirection(0);
            hero.moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.setDirection(1);
            hero.moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.setDirection(2);
            hero.moveDown();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.setDirection(3);
            hero.moveLeft();
        }
        //用户按下J发射子弹->就会开辟一个新的线程
        if (e.getKeyCode() == KeyEvent.VK_J) {
            hero.shotStart();
        }
        //坦克重绘
//        this.repaint();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //每隔一段时间重绘绘图区
            this.repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
