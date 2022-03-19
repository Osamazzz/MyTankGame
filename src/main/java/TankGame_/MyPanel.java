package TankGame_;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author Obamazzz
 * @version 2.0
 * 绘图区域
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义坦克
    Hero hero;
    Vector<Enemy> enemies = new Vector<>();
    //用于存放炸弹,当子弹击中坦克时就加入一个bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();
    int count = 0;
    //定义两张图片用于显示爆炸效果
    Image image1;
    Image image2;

    public MyPanel() {
        hero = new Hero(200, 400);//初始化自己坦克
        for (int i = 0; i < 5; i++) {//画三个敌人坦克并将其放入vector集合中
            Enemy enemy = new Enemy(200 + 100 * i, 100);
            enemy.setDirection(2);
            //启动敌人线程
            new Thread(enemy).start();
            Shot shot = new Shot(enemy.getX() + 20, enemy.getY() + 60, enemy.getDirection());//给敌人加入子弹
            enemy.shots.add(shot);
            //启动shot线程
            new Thread(shot).start();
            enemies.add(enemy);
        }
        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/img_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/img_2.png"));
    }

    @Override
    public void paint(Graphics g) {//画板

        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形，默认黑色
        //画出自己的坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirection(), 0);//自己的坦克
        //画出敌人的坦克
        for (int i = 0; i < enemies.size(); i++) {
            //取出集合中的敌人坦克
            Enemy enemyTank = enemies.get(i);
            if (enemyTank.isLive()) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirection(), 1);
            }
//            count++;
//            if (count == 100) {//每隔一段时间敌人发射子弹
//                Shot shot_ = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());//给敌人加入子弹
//                enemyTank.shots.add(shot_);
//                //启动shot线程
//                new Thread(shot_).start();
//                count = 0;
//            }
            //画出enemyTank的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出敌人的子弹
                Shot shot = enemyTank.shots.get(j);
                //画出敌人的子弹
                if (shot.isLive()) {
                    drawBullet(shot.getX(), shot.getY(), g, shot.getDirect());
                } else {
                    //死亡时从集合中移除
                    enemyTank.shots.remove(shot);
                }

            }
        }
        //按下J时画出自己的子弹
        g.setColor(Color.WHITE);//白色子弹
        for (int i = 0; i < hero.shotList.size(); i++) {//遍历自己的子弹集合
            if (hero.shotList.get(i) != null && hero.shotList.get(i).isLive()) {
                drawBullet(hero.shotList.get(i).getX(), hero.shotList.get(i).getY(), g, hero.shotList.get(i).getDirect());
            }
//            else {
//                hero.shotList.remove(hero.shotList.get(i));//将已经死亡的子弹移除vector集合中
//            }
            if (!(hero.shotList.get(i).isLive())) {
                hero.shotList.remove(hero.shotList.get(i));//将已经死亡的子弹从vector集合中移除
            }
        }
        //如果爆炸集合中有对象，就画出
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            Bomb bomb = bombs.get(i);
            if (bomb.life > 5) {
                g.drawImage(image1, bomb.x, bomb.y, 60, 60, this);
            } else {
                g.drawImage(image2, bomb.x, bomb.y, 60, 60, this);
            }
            bomb.lifeDecrease();
            if (bomb.life == 0) {
                bombs.remove(bomb);
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

    //判断我方子弹是否击中敌人
    public void hitTank(Shot s, Enemy enemy) {
        switch (enemy.getDirection()) {
            case 0://向上
            case 2://向下
                //子弹进入坦克区域
                if (s.getX() > enemy.getX() && s.getX() < enemy.getX() + 40
                        && s.getY() > enemy.getY() && s.getY() < enemy.getY() + 60) {
                    s.setLive(false);//只是让子弹消失，子弹线程并未退出(假死亡)
                    enemy.setLive(false);
                    enemies.remove(enemy);//死亡则移除
                    //创建Bomb对象
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                }
                break;
            case 1:
            case 3:
                if (s.getX() > enemy.getX() - 10 && s.getX() < enemy.getX() + 50
                        && s.getY() > enemy.getY() + 10 && s.getY() < enemy.getY() + 50) {
                    s.setLive(false);
                    enemy.setLive(false);
                    enemies.remove(enemy);//死亡则移除
                    //创建Bomb对象
                    Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                    bombs.add(bomb);
                }
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
            //在这里可以写一个判断语句，让我方坦克一次只能发射一颗子弹
//            if (hero.shot==null || !hero.shot.isLive()) {
//                hero.shotStart();
//            }
        }
        //坦克重绘
//        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断是否击中了敌人坦克
            for (int j = 0; j < hero.shotList.size(); j++) {
                //如果自己坦克发射的子弹还存活着
                if (hero.shotList.get(j) != null && hero.shotList.get(j).isLive()) {
                    //千万别用foreach进行遍历，因为foreach本质是迭代器，
                    // 如果在迭代时同时进行修改操作就会抛出异常！
                    for (int i =0 ;i<enemies.size(); i++) {//遍历所有坦克
                        hitTank(hero.shotList.get(j), enemies.get(i));
                    }
                }
            }
            //每隔一段时间重绘绘图区
            this.repaint();
        }
    }
}
